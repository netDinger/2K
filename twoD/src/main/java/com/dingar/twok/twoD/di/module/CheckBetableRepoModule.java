package com.dingar.twok.twoD.di.module;

import com.dingar.twok.twoD.data.repositoryImpl.CheckBetableRepoImpl;
import com.dingar.twok.twoD.di.scope.FeatureScope;
import com.dingar.twok.twoD.domain.repository.CheckBetableRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class CheckBetableRepoModule {
    @FeatureScope
    @Binds
    public abstract CheckBetableRepository provideRepository(CheckBetableRepoImpl repoImpl);
}
