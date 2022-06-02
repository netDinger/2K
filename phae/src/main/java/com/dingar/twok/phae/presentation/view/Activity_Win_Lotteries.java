package com.dingar.twok.phae.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.dingar.twok.core.util.DateUtil;
import com.dingar.twok.phae.R;
import com.dingar.twok.phae.data.model.WinLotteryModel;
import com.dingar.twok.phae.di.component.ComponentProviderPhae;
import com.dingar.twok.phae.di.component.PhaeWinLotteryComponent;
import com.dingar.twok.phae.presentation.contract.WinLotteryContract;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * show the win lottery history
 * @see WinLotteryContract for better understanding
 */
public class Activity_Win_Lotteries extends AppCompatActivity implements WinLotteryContract.View {

    @Inject
    public WinLotteryContract.Presenter presenter;

    PhaeWinLotteryComponent component;

    WinLotteryRecyclerViewAdapter winLotteryRecyclerViewAdapter;

    private ArrayList<WinLotteryModel> winLotteryArrayList;
    private RecyclerView winHistoryRecyclerview;
    private TextView luckyNumber,updated_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phae_win_lotteries);

        //initiate component and inject the view
        component = ((ComponentProviderPhae)getApplicationContext()).providePhaeWinLotteryComponent();
        component.inject(this); //inject the view

        widgets();
        initiate();
    }


    @Override
    public void onLuckyHistoryLoaded(WinLotteryModel model) {
        winLotteryArrayList.add(model);
        winLotteryRecyclerViewAdapter.notifyItemInserted(winLotteryArrayList.size()-1);
    }

    @Override
    public void onCurrentTwoDLoaded(String twoD) {
        luckyNumber.setText(twoD);
        updated_date.setText(DateUtil.timeStampToDate(String.valueOf(System.currentTimeMillis())));
    }

    private void widgets(){
        winHistoryRecyclerview = findViewById(R.id.winHistory);
        //current TwoD result
        luckyNumber = findViewById(R.id.lottery_number);
        updated_date = findViewById(R.id.updated_date);
    }

    private void initiate(){
        winLotteryArrayList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        winHistoryRecyclerview.setLayoutManager(layoutManager);
        winLotteryRecyclerViewAdapter =
                new WinLotteryRecyclerViewAdapter(winLotteryArrayList);
        winHistoryRecyclerview.setAdapter(winLotteryRecyclerViewAdapter);

        presenter.setView(this);
        //Tell presenter to load all lucky number within a month
        presenter.loadLuckyHistory();
    }

}