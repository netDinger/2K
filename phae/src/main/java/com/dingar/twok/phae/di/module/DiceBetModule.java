package com.dingar.twok.phae.di.module;

import com.dingar.twok.phae.di.scope.FeatureScope;
import com.dingar.twok.phae.domain.interactor.BetableTimeUseCase;
import com.dingar.twok.phae.domain.interactor.CheckBetableUseCase;
import com.dingar.twok.phae.domain.interactor.CountDownUseCase;
import com.dingar.twok.phae.domain.interactor.LoadBetsUseCase;
import com.dingar.twok.phae.presentation.contract.DiceBetContract;
import com.dingar.twok.phae.presentation.presenter.DiceBetPresenter;

import dagger.Module;
import dagger.Provides;

@Module(includes = {LoadBetRepoModule.class,
        GetBalanceRepoModule.class,
        TimeRemainRepoModule.class,
        BetableTimeRepoModule.class,
CheckBetableRepoModule.class})
public class DiceBetModule {

    @FeatureScope
    @Provides
    public DiceBetContract.Presenter providePresenter(LoadBetsUseCase loadBetsUseCase,
                                                      CountDownUseCase countDownUseCase,
                                                      BetableTimeUseCase betableTimeUseCase,
                                                      CheckBetableUseCase checkBetableUseCase){
        return new DiceBetPresenter(loadBetsUseCase,
                countDownUseCase,betableTimeUseCase,checkBetableUseCase);
    }
}
