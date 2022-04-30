package com.dingar.twok.auth.di.module;

import com.dingar.twok.auth.di.scope.FeatureScope;
import com.dingar.twok.auth.domain.interactor.SignupUseCase;
import com.dingar.twok.auth.presentation.contract.SignupContract;
import com.dingar.twok.auth.presentation.presenter.SignupPresenter;
import com.google.firebase.auth.FirebaseAuth;

import dagger.Module;
import dagger.Provides;

@Module(includes = {SignupRepoModule.class,VerifyCodeModule.class})
public class SignupModule {

    @FeatureScope
    @Provides
    public SignupContract.Presenter providePresenter(SignupUseCase signupUseCase){
        return new SignupPresenter(signupUseCase);
    }

}
