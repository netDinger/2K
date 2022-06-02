package com.dingar.twok.twoK.di.module;

import com.dingar.twok.twoK.di.scope.FeatureScope;
import com.dingar.twok.twoK.domain.interactor.BetUseCase;
import com.dingar.twok.twoK.domain.interactor.GetBalanceUseCase;
import com.dingar.twok.twoK.presentation.contract.BetListContract;
import com.dingar.twok.twoK.presentation.presenter.BetSlipPresenter;

import dagger.Module;
import dagger.Provides;

@Module(includes = {BetRepoModule.class,GetBalanceRepoModule.class})
public class BetSlipModule {

    @FeatureScope
    @Provides
    public BetListContract.Presenter provideBetSlipPresenter(BetUseCase betUseCase,
                                                             GetBalanceUseCase getBalanceUseCase){
        return new BetSlipPresenter(betUseCase,getBalanceUseCase);
    }

}
