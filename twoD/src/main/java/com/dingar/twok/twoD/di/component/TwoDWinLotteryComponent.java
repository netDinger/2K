package com.dingar.twok.twoD.di.component;

import com.dingar.twok.twoD.di.module.WinLotteryModule;
import com.dingar.twok.twoD.di.scope.FeatureScope;
import com.dingar.twok.twoD.presentation.view.Activity_Win_Lotteries;

import dagger.Subcomponent;

@FeatureScope
@Subcomponent(modules = WinLotteryModule.class)
public interface TwoDWinLotteryComponent {

    @Subcomponent.Builder
    interface Builder{
        TwoDWinLotteryComponent build();
    }

    void inject(Activity_Win_Lotteries activity_win_lotteries);
}
