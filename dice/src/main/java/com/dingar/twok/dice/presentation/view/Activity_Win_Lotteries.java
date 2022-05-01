package com.dingar.twok.dice.presentation.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dingar.twok.dice.R;
import com.dingar.twok.dice.data.model.WinLotteryModel;
import com.dingar.twok.dice.di.component.BetSlipComponentProvider;
import com.dingar.twok.dice.di.component.WinLotteryComponent;
import com.dingar.twok.dice.presentation.contract.WinLotteryContract;

import javax.inject.Inject;

public class Activity_Win_Lotteries extends AppCompatActivity implements WinLotteryContract.View {

    @Inject
    public WinLotteryContract.Presenter presenter;

    WinLotteryComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_lotteries);

        component = ((BetSlipComponentProvider)getApplicationContext()).provideWinLotteryComponent();
        component.inject(this);

        presenter.loadLuckyHistory();

    }


    @Override
    public void onLuckyHistoryLoaded(WinLotteryModel model) {
    }
}