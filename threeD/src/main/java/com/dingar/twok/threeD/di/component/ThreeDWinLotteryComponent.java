package com.dingar.twok.threeD.di.component;

import com.dingar.twok.threeD.di.module.WinLotteryModule;
import com.dingar.twok.threeD.di.scope.FeatureScope;
import com.dingar.twok.threeD.presentation.view.Activity_Win_Lotteries;

import dagger.Subcomponent;

@FeatureScope
@Subcomponent(modules = WinLotteryModule.class)
public interface ThreeDWinLotteryComponent {

    @Subcomponent.Builder
    interface Builder{
        ThreeDWinLotteryComponent build();
    }

    void inject(Activity_Win_Lotteries activity_win_lotteries);
}
