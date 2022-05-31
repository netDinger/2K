package com.dingar.twok.threeD.di.module;

import com.dingar.twok.threeD.di.scope.FeatureScope;
import com.dingar.twok.threeD.domain.interactor.WinHistoryUseCase;
import com.dingar.twok.threeD.presentation.contract.WinLotteryContract;
import com.dingar.twok.threeD.presentation.presenter.WinLotteryPresenter;

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
