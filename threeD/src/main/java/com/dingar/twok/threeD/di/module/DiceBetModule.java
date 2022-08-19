package com.dingar.twok.threeD.di.module;

import com.dingar.twok.threeD.di.scope.FeatureScope;
import com.dingar.twok.threeD.domain.interactor.BetableTimeUseCase;
import com.dingar.twok.threeD.domain.interactor.CheckBetableUseCase;
import com.dingar.twok.threeD.domain.interactor.CountDownUseCase;
import com.dingar.twok.threeD.domain.interactor.LoadBetsUseCase;
import com.dingar.twok.threeD.presentation.contract.DiceBetContract;
import com.dingar.twok.threeD.presentation.presenter.DiceBetPresenter;

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
                                                      BetableTimeUseCase betableTimeUseCase, CheckBetableUseCase checkBetableUseCase){
        return new DiceBetPresenter(loadBetsUseCase,
                countDownUseCase,betableTimeUseCase,checkBetableUseCase);
    }
}
