package com.dingar.twok.twoD.di.module;


import com.dingar.twok.twoD.di.scope.FeatureScope;
import com.dingar.twok.twoD.domain.interactor.BetableTimeUseCase;
import com.dingar.twok.twoD.domain.interactor.CheckBetableUseCase;
import com.dingar.twok.twoD.domain.interactor.CountDownUseCase;
import com.dingar.twok.twoD.domain.interactor.LoadBetsUseCase;
import com.dingar.twok.twoD.presentation.contract.DiceBetContract;
import com.dingar.twok.twoD.presentation.presenter.DiceBetPresenter;

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
