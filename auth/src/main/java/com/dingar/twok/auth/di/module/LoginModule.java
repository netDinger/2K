package com.dingar.twok.auth.di.module;

import com.dingar.twok.auth.di.scope.FeatureScope;
import com.dingar.twok.auth.domain.interactor.LoginUsecase;
import com.dingar.twok.auth.presentation.contract.Login_Contract;
import com.dingar.twok.auth.presentation.presenter.LoginPresenter;

import dagger.Module;
import dagger.Provides;

@Module(includes = LoginRepoModule.class)
public class LoginModule {

    @FeatureScope
    @Provides
    public Login_Contract.Presenter provideLoginPresenter(LoginUsecase loginUsecase){
        return new LoginPresenter(loginUsecase);
    }
}
