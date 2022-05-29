package com.dingar.twok.twoD.di.module;


import com.dingar.twok.twoD.di.scope.FeatureScope;
import com.dingar.twok.twoD.domain.interactor.BetUseCase;
import com.dingar.twok.twoD.domain.interactor.GetBalanceUseCase;
import com.dingar.twok.twoD.presentation.contract.BetListContract;
import com.dingar.twok.twoD.presentation.presenter.BetSlipPresenter;

import dagger.Module;
import dagger.Provides;

@Module(includes = {BetRepoModule.class, GetBalanceRepoModule.class})
public class BetSlipModule {

    @FeatureScope
    @Provides
    public BetListContract.Presenter provideBetSlipPresenter(BetUseCase betUseCase,
                                                             GetBalanceUseCase getBalanceUseCase){
        return new BetSlipPresenter(betUseCase,getBalanceUseCase);
    }

}
