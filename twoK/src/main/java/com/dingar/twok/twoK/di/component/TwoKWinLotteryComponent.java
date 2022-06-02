package com.dingar.twok.twoK.di.component;

import com.dingar.twok.twoK.di.module.WinLotteryModule;
import com.dingar.twok.twoK.di.scope.FeatureScope;
import com.dingar.twok.twoK.presentation.view.Activity_Win_Lotteries;

import dagger.Subcomponent;

@FeatureScope
@Subcomponent(modules = WinLotteryModule.class)
public interface TwoKWinLotteryComponent {

    @Subcomponent.Builder
    interface Builder{
        TwoKWinLotteryComponent build();
    }

    void inject(Activity_Win_Lotteries activity_win_lotteries);
}
