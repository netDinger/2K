package com.dingar.twok.dice.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.dingar.twok.dice.R;
import com.dingar.twok.dice.data.model.WinLotteryModel;
import com.dingar.twok.dice.di.component.BetSlipComponentProvider;
import com.dingar.twok.dice.di.component.WinLotteryComponent;
import com.dingar.twok.dice.presentation.contract.WinLotteryContract;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * show the win lottery history
 */
public class Activity_Win_Lotteries extends AppCompatActivity implements WinLotteryContract.View {

    @Inject
    public WinLotteryContract.Presenter presenter;

    WinLotteryComponent component;

    WinLotteryRecyclerViewAdapter winLotteryRecyclerViewAdapter;

    private ArrayList<WinLotteryModel> winLotteryArrayList;
    private RecyclerView winHistoryRecyclerview;
    private TextView luckyNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_lotteries);

        component = ((BetSlipComponentProvider)getApplicationContext()).provideWinLotteryComponent();
        component.inject(this);

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
    }

    private void widgets(){
        winHistoryRecyclerview = findViewById(R.id.winHistory);
        //current TwoD result
        luckyNumber = findViewById(R.id.lottery_number);
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