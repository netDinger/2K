package com.dingar.twok.twoK.di.module;

import com.dingar.twok.twoK.di.scope.FeatureScope;
import com.dingar.twok.twoK.domain.interactor.WinHistoryUseCase;
import com.dingar.twok.twoK.presentation.contract.WinLotteryContract;
import com.dingar.twok.twoK.presentation.presenter.WinLotteryPresenter;

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
