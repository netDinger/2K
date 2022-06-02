package com.dingar.twok.twoK.di.module;

import com.dingar.twok.twoK.di.scope.FeatureScope;
import com.dingar.twok.twoK.domain.interactor.BetableTimeUseCase;
import com.dingar.twok.twoK.domain.interactor.CountDownUseCase;
import com.dingar.twok.twoK.domain.interactor.LoadBetsUseCase;
import com.dingar.twok.twoK.presentation.contract.DiceBetContract;
import com.dingar.twok.twoK.presentation.presenter.DiceBetPresenter;

import dagger.Module;
import dagger.Provides;

@Module(includes = {LoadBetRepoModule.class,
        GetBalanceRepoModule.class,
        TimeRemainRepoModule.class,
        BetableTimeRepoModule.class})
public class DiceBetModule {

    @FeatureScope
    @Provides
    public DiceBetContract.Presenter providePresenter(LoadBetsUseCase loadBetsUseCase,
                                                      CountDownUseCase countDownUseCase,
                                                      BetableTimeUseCase betableTimeUseCase){
        return new DiceBetPresenter(loadBetsUseCase,
                countDownUseCase,betableTimeUseCase);
    }
}
