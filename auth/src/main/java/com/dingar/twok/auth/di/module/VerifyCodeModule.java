package com.dingar.twok.auth.di.module;

import com.dingar.twok.auth.di.scope.FeatureScope;
import com.dingar.twok.auth.domain.interactor.UploadNewUserUseCase;
import com.dingar.twok.auth.domain.interactor.VerifyCodeUseCase;
import com.dingar.twok.auth.presentation.contract.VerifyCode_Contract;
import com.dingar.twok.auth.presentation.presenter.VerifyCodePresenter;

import dagger.Module;
import dagger.Provides;

@Module(includes = {VerifyCodeRepoModule.class,UploadUserRepoModule.class})
public class VerifyCodeModule {

    @FeatureScope
    @Provides
    public VerifyCode_Contract.Presenter providesPresenter(VerifyCodeUseCase useCase,
                                                           UploadNewUserUseCase userUseCase){
        return new VerifyCodePresenter(useCase,userUseCase);
    }
}
