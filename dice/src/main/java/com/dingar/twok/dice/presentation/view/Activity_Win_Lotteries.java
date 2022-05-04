package com.dingar.twok.dice.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.dingar.twok.dice.R;
import com.dingar.twok.dice.data.model.WinLotteryModel;
import com.dingar.twok.dice.di.component.BetSlipComponentProvider;
import com.dingar.twok.dice.di.component.WinLotteryComponent;
import com.dingar.twok.dice.presentation.contract.WinLotteryContract;

import java.util.ArrayList;

import javax.inject.Inject;

public class Activity_Win_Lotteries extends AppCompatActivity implements WinLotteryContract.View {

    @Inject
    public WinLotteryContract.Presenter presenter;

    WinLotteryComponent component;

    private ArrayList<WinLotteryModel> winLotteryArrayList;
    private RecyclerView winHistoryRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_lotteries);

        component = ((BetSlipComponentProvider)getApplicationContext()).provideWinLotteryComponent();
        component.inject(this);

        initiate();
        widgets();
    }


    @Override
    public void onLuckyHistoryLoaded(WinLotteryModel model) {
        winLotteryArrayList.add(model);
    }

    private void initiate(){
        winLotteryArrayList = new ArrayList<>();
        //Tell presenter to load all lucky number within a month
        presenter.loadLuckyHistory();
    }

    private void widgets(){
        winHistoryRecyclerview = findViewById(R.id.winHistory);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        winHistoryRecyclerview.setLayoutManager(layoutManager);
        WinLotteryRecyclerViewAdapter winLotteryRecyclerViewAdapter =
                new WinLotteryRecyclerViewAdapter(winLotteryArrayList);
        winHistoryRecyclerview.setAdapter(winLotteryRecyclerViewAdapter);
    }


}