package com.dingar.twok.auth.di.module;

import com.dingar.twok.auth.data.repository.VerifyCodeRepositoryImpl;
import com.dingar.twok.auth.di.scope.FeatureScope;
import com.dingar.twok.auth.domain.repository.VerifyCodeRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class VerifyCodeRepoModule {

    @FeatureScope
    @Binds
    abstract VerifyCodeRepository provideVerifyCodeRepository(VerifyCodeRepositoryImpl verifyCodeRepository);
}
