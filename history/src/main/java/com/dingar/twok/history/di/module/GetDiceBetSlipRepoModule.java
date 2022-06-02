package com.dingar.twok.history.di.module;

import com.dingar.twok.history.data.repositoryImpl.GetDiceBetSlipRepoImpl;
import com.dingar.twok.history.di.scope.FeatureScope;
import com.dingar.twok.history.domain.repository.GetDiceBetSlipsRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class GetDiceBetSlipRepoModule {

    @FeatureScope
    @Binds
    public abstract GetDiceBetSlipsRepository provideRepository(GetDiceBetSlipRepoImpl getDiceBetSlipRepo);
}
