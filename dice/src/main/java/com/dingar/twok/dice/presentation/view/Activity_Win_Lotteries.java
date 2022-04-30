package com.dingar.twok.dice.presentation.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dingar.twok.dice.R;
import com.dingar.twok.dice.presentation.contract.WinLotteryContract;

public class Activity_Win_Lotteries extends AppCompatActivity implements WinLotteryContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_lotteries);
    }



}