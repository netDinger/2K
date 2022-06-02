package com.dingar.twok.twoK.di.module;

import com.dingar.twok.twoK.data.BetRepositoryImpl;
import com.dingar.twok.twoK.di.scope.FeatureScope;
import com.dingar.twok.twoK.domain.repository.BetRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class BetRepoModule {

    @FeatureScope
    @Binds
    abstract BetRepository provideBetRepository(BetRepositoryImpl betRepository);
}
