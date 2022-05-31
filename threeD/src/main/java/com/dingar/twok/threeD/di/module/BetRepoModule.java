package com.dingar.twok.threeD.di.module;

import com.dingar.twok.threeD.data.BetRepositoryImpl;
import com.dingar.twok.threeD.di.scope.FeatureScope;
import com.dingar.twok.threeD.domain.repository.BetRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class BetRepoModule {

    @FeatureScope
    @Binds
    abstract BetRepository provideBetRepository(BetRepositoryImpl betRepository);
}
