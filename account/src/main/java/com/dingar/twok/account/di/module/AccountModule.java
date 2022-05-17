package com.dingar.twok.account.di.module;

import com.dingar.twok.account.di.scope.FeatureScope;
import com.dingar.twok.account.domain.interactor.GetBalanceUseCase;
import com.dingar.twok.account.domain.interactor.GetUserInfoUseCase;
import com.dingar.twok.account.presentation.contract.AccountContract;
import com.dingar.twok.account.presentation.presenter.AccountPresenter;

import dagger.Module;
import dagger.Provides;

@Module(includes = {GetBalanceRepositoryModule.class,GetUserInfoRepositoryModule.class})
public class AccountModule {

    @FeatureScope
    @Provides
    public AccountContract.Presenter providePresenter(GetBalanceUseCase getBalanceUseCase,
                                                      GetUserInfoUseCase getUserInfoUseCase){
        return new AccountPresenter(getBalanceUseCase,getUserInfoUseCase);
    }
}
