package com.dingar.twok.dice.di.component;

import com.dingar.twok.dice.di.module.WinLotteryModule;
import com.dingar.twok.dice.di.scope.FeatureScope;
import com.dingar.twok.dice.presentation.view.Activity_Win_Lotteries;

import dagger.Subcomponent;

@FeatureScope
@Subcomponent(modules = WinLotteryModule.class)
public interface WinLotteryComponent {

    @Subcomponent.Builder
    interface Builder{
        WinLotteryComponent build();
    }

    void inject(Activity_Win_Lotteries activity_win_lotteries);
}
