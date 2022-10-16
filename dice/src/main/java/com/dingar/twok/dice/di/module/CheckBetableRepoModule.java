package com.dingar.twok.dice.di.module;

import com.dingar.twok.dice.data.repositoryImpl.CheckBetableRepoImpl;
import com.dingar.twok.dice.di.scope.FeatureScope;
import com.dingar.twok.dice.domain.repository.CheckBetableRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class CheckBetableRepoModule {
    @FeatureScope
    @Binds
    public abstract CheckBetableRepository provideRepository(CheckBetableRepoImpl repoImpl);
}
