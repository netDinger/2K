package com.dingar.twok.threeD.di.module;

import com.dingar.twok.threeD.di.scope.FeatureScope;
import com.dingar.twok.threeD.domain.interactor.BetUseCase;
import com.dingar.twok.threeD.domain.interactor.GetBalanceUseCase;
import com.dingar.twok.threeD.domain.interactor.GetPointUseCase;
import com.dingar.twok.threeD.presentation.contract.BetListContract;
import com.dingar.twok.threeD.presentation.presenter.BetSlipPresenter;

import dagger.Module;
import dagger.Provides;

@Module(includes = {BetRepoModule.class,GetBalanceRepoModule.class,GetPointRepoModule.class})
public class BetSlipModule {

    @FeatureScope
    @Provides
    public BetListContract.Presenter provideBetSlipPresenter(BetUseCase betUseCase,
                                                             GetBalanceUseCase getBalanceUseCase,
                                                             GetPointUseCase getPointUseCase){
        return new BetSlipPresenter(betUseCase,getBalanceUseCase,getPointUseCase);
    }

}
