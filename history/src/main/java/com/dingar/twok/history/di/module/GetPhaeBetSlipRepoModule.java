package com.dingar.twok.history.di.module;

import com.dingar.twok.history.data.repositoryImpl.GetPhaeBetSlipRepoImpl;
import com.dingar.twok.history.di.scope.FeatureScope;
import com.dingar.twok.history.domain.repository.GetPhaeBetSlipRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class GetPhaeBetSlipRepoModule {

    @FeatureScope
    @Binds
    public abstract GetPhaeBetSlipRepository provideRepository(GetPhaeBetSlipRepoImpl getPhaeBetSlipRepo);
}
