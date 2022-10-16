package com.dingar.twok.threeD.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.dingar.twok.core.util.DateUtil;
import com.dingar.twok.threeD.R;
import com.dingar.twok.threeD.data.model.WinLotteryModel;
import com.dingar.twok.threeD.di.component.ComponentProviderThreeD;
import com.dingar.twok.threeD.di.component.ThreeDWinLotteryComponent;
import com.dingar.twok.threeD.presentation.contract.WinLotteryContract;

import java.util.Objects;

import javax.inject.Inject;

/**
 * show the win lottery history
 * @see WinLotteryContract for better understanding
 */
public class Activity_Win_Lotteries extends AppCompatActivity implements WinLotteryContract.View {

    @Inject
    public WinLotteryContract.Presenter presenter;

    ThreeDWinLotteryComponent component;

    WinLotteryRecyclerViewAdapter winLotteryRecyclerViewAdapter;

    private RecyclerView winHistoryRecyclerview;
    private TextView luckyNumber,updated_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threed_win_lotteries);

        //initiate component and inject the view
        component = ((ComponentProviderThreeD)getApplicationContext()).provideThreeDWinLotteryComponent();
        component.inject(this); //inject the view

        widgets();
        initiate();
    }

    @Override public void onLuckyHistoryLoaded(WinLotteryModel model) {
        winLotteryRecyclerViewAdapter.addData(model);
    }

    @Override
    public void onCurrentTwoDLoaded(String twoD) {
        luckyNumber.setText(twoD);
        try {
            updated_date.setText(DateUtil.timeStampToDate(String.valueOf(System.currentTimeMillis())));
        }catch (Exception e){
            Log.e("error",e.getMessage());
        }
    }

    private void widgets(){
        addToolbar();
        winHistoryRecyclerview = findViewById(R.id.winHistory);
        //current TwoD result
        luckyNumber = findViewById(R.id.lottery_number);
        updated_date = findViewById(R.id.updateDate);
    }

    private void initiate(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        winHistoryRecyclerview.setLayoutManager(layoutManager);
        winLotteryRecyclerViewAdapter =
                new WinLotteryRecyclerViewAdapter();
        winHistoryRecyclerview.setAdapter(winLotteryRecyclerViewAdapter);

        presenter.setView(this);
        //Tell presenter to load all lucky number within a month
        presenter.loadLuckyHistory();
    }

    private void addToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed()); }

}