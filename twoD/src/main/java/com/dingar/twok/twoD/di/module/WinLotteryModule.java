package com.dingar.twok.twoD.di.module;


import com.dingar.twok.twoD.di.scope.FeatureScope;
import com.dingar.twok.twoD.domain.interactor.WinHistoryUseCase;
import com.dingar.twok.twoD.presentation.contract.WinLotteryContract;
import com.dingar.twok.twoD.presentation.presenter.WinLotteryPresenter;

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
