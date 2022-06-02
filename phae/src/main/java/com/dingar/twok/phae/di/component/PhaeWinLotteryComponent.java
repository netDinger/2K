package com.dingar.twok.phae.di.component;

import com.dingar.twok.phae.di.module.WinLotteryModule;
import com.dingar.twok.phae.di.scope.FeatureScope;
import com.dingar.twok.phae.presentation.view.Activity_Win_Lotteries;

import dagger.Subcomponent;

@FeatureScope
@Subcomponent(modules = WinLotteryModule.class)
public interface PhaeWinLotteryComponent {

    @Subcomponent.Builder
    interface Builder{
        PhaeWinLotteryComponent build();
    }

    void inject(Activity_Win_Lotteries activity_win_lotteries);
}
