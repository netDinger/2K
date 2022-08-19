package com.dingar.twok.twoK.di.module;

import com.dingar.twok.twoK.data.CheckBetableRepositoryImpl;
import com.dingar.twok.twoK.di.scope.FeatureScope;
import com.dingar.twok.twoK.domain.repository.CheckBetableRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class CheckBetableRepoModule {

    @FeatureScope
    @Binds
    abstract CheckBetableRepository provideRepository(CheckBetableRepositoryImpl checkBetableRepository);
}
