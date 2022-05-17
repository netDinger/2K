package com.dingar.twok.account.di.module;

import com.dingar.twok.account.data.repositoryImpl.GetBalanceRepositoryImpl;
import com.dingar.twok.account.di.scope.FeatureScope;
import com.dingar.twok.account.domain.repository.GetBalanceRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class GetBalanceRepositoryModule {

    @FeatureScope
    @Binds
    public abstract GetBalanceRepository provideGetBalanceRepository(GetBalanceRepositoryImpl getBalanceRepository);
}
