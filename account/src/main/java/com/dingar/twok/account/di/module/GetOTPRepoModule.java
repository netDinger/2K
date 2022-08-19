package com.dingar.twok.account.di.module;

import com.dingar.twok.account.data.repositoryImpl.GetOTPRepoImpl;
import com.dingar.twok.account.di.scope.FeatureScope;
import com.dingar.twok.account.domain.repository.GetOTPRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class GetOTPRepoModule {
    @FeatureScope
    @Binds
    public abstract GetOTPRepository provideRepository(GetOTPRepoImpl repoImpl);
}
