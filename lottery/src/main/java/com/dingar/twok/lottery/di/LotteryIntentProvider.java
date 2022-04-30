package com.dingar.twok.lottery.di;

import android.app.Activity;
import android.content.Intent;

import com.dingar.twok.core.IntentRoute;
import com.dingar.twok.lottery.presentation.view.Activity_Lottery_Main;

import javax.inject.Inject;

public class LotteryIntentProvider implements IntentRoute {

    @Inject
    public LotteryIntentProvider(){}

    @Override
    public void setData(String data) {
    }

    @Override
    public void present(Activity root) {
        Intent intent = new Intent(root, Activity_Lottery_Main.class);
        root.startActivity(intent);
    }
}
