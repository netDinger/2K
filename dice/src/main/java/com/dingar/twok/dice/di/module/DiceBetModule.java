package com.dingar.twok.dice.di.module;

import com.dingar.twok.dice.di.scope.FeatureScope;
import com.dingar.twok.dice.domain.interactor.BetableTimeUseCase;
import com.dingar.twok.dice.domain.interactor.CheckBetableUseCase;
import com.dingar.twok.dice.domain.interactor.CountDownUseCase;
import com.dingar.twok.dice.domain.interactor.GetBalanceUseCase;
import com.dingar.twok.dice.domain.interactor.LoadBetsUseCase;
import com.dingar.twok.dice.presentation.contract.DiceBetContract;
import com.dingar.twok.dice.presentation.presenter.DiceBetPresenter;

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
