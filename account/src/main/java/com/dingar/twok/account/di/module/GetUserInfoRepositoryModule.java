package com.dingar.twok.account.di.module;

import com.dingar.twok.account.data.repositoryImpl.GetUserInfoRepositoryImpl;
import com.dingar.twok.account.di.scope.FeatureScope;
import com.dingar.twok.account.domain.repository.GetUserInfoRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class GetUserInfoRepositoryModule {

    @FeatureScope
    @Binds
    public abstract GetUserInfoRepository provideUserInfoRepository(GetUserInfoRepositoryImpl getUserInfoRepository);
}
