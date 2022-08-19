package com.dingar.twok.account.di.module;

import com.dingar.twok.account.di.scope.FeatureScope;
import com.dingar.twok.account.domain.interactor.GetBalanceUseCase;
import com.dingar.twok.account.domain.interactor.GetOTPUseCase;
import com.dingar.twok.account.presentation.contract.BalanceContract;
import com.dingar.twok.account.presentation.presenter.BalancePresenter;

import dagger.Module;
import dagger.Provides;

@Module(includes = {GetBalanceRepositoryModule.class,GetOTPRepoModule.class})
public class BalanceModule {

    @FeatureScope
    @Provides
    public BalanceContract.Presenter providePresenter(GetBalanceUseCase getBalanceUseCase, GetOTPUseCase getOTPUseCase){
        return new BalancePresenter(getBalanceUseCase,getOTPUseCase);
    }
}
