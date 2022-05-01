package com.dingar.twok.dice.di.module;

import com.dingar.twok.dice.di.scope.FeatureScope;
import com.dingar.twok.dice.domain.interactor.WinHistoryUseCase;
import com.dingar.twok.dice.presentation.contract.WinLotteryContract;
import com.dingar.twok.dice.presentation.presenter.WinLotteryPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class WinLotteryModule {

    @FeatureScope
    @Provides
    public WinLotteryContract.Presenter provideWinLotteryPresenter(WinHistoryUseCase winHistoryUseCase){
        return new WinLotteryPresenter(winHistoryUseCase);
    }
}
