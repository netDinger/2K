package com.dingar.twok.dice.di.module;

import com.dingar.twok.dice.di.scope.FeatureScope;
import com.dingar.twok.dice.domain.interactor.GetBalanceUseCase;
import com.dingar.twok.dice.domain.interactor.LoadBetsUseCase;
import com.dingar.twok.dice.presentation.contract.DiceBetContract;
import com.dingar.twok.dice.presentation.presenter.DiceBetPresenter;

import dagger.Module;
import dagger.Provides;

@Module(includes = {LoadBetRepoModule.class,GetBalanceRepoModule.class})
public class DiceBetModule {

    @FeatureScope
    @Provides
    public DiceBetContract.Presenter providePresenter(LoadBetsUseCase loadBetsUseCase,
                                                      GetBalanceUseCase getBalanceUseCase){
        return new DiceBetPresenter(loadBetsUseCase,getBalanceUseCase);
    }
}
