package com.dingar.twok.phae.di.module;

import com.dingar.twok.phae.data.repoImpl.CheckBetableRepoImpl;
import com.dingar.twok.phae.di.scope.FeatureScope;
import com.dingar.twok.phae.domain.repository.CheckBetableRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class CheckBetableRepoModule {
    @FeatureScope
    @Binds
    public abstract CheckBetableRepository provideRepository(CheckBetableRepoImpl repoImpl);
}
