package com.dingar.twok.phae.di.module;


import com.dingar.twok.phae.di.scope.FeatureScope;
import com.dingar.twok.phae.domain.interactor.WinHistoryUseCase;
import com.dingar.twok.phae.presentation.contract.WinLotteryContract;
import com.dingar.twok.phae.presentation.presenter.WinLotteryPresenter;

import dagger.Module;
import dagger.Provides;

@Module(includes = WinLotteryRepoModule.class)
public class WinLotteryModule {

    @FeatureScope
    @Provides
    public WinLotteryContract.Presenter provideWinLotteryPresenter(WinHistoryUseCase winHistoryUseCase){
        return new WinLotteryPresenter(winHistoryUseCase);
    }
}
