package com.dingar.twok.phae.di.module;

import com.dingar.twok.phae.di.scope.FeatureScope;
import com.dingar.twok.phae.domain.interactor.BetUseCase;
import com.dingar.twok.phae.domain.interactor.GetBalanceUseCase;
import com.dingar.twok.phae.presentation.contract.BetListContract;
import com.dingar.twok.phae.presentation.presenter.BetSlipPresenter;

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
