package com.dingar.twok.history.di.module;

import com.dingar.twok.history.di.scope.FeatureScope;
import com.dingar.twok.history.domain.interactor.GetBetSlipHistoryUseCase;
import com.dingar.twok.history.presentation.contract.HistoryContract;
import com.dingar.twok.history.presentation.presenter.HistoryPresenter;

import dagger.Module;
import dagger.Provides;

@Module(includes = {Get2DBetSlipRepoModule.class,
        Get3DBetSlipRepoModule.class,
        GetDiceBetSlipRepoModule.class,
        GetPhaeBetSlipRepoModule.class,
        Get2KBetSlipRepoModule.class})
public class HistoryModule {

    @FeatureScope
    @Provides
    public HistoryContract.Presenter providePresenter(GetBetSlipHistoryUseCase getBetSlipHistoryUseCase){
        return new HistoryPresenter(getBetSlipHistoryUseCase);
    }
}
