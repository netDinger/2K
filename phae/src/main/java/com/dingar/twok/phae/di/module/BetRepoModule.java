package com.dingar.twok.phae.di.module;

import com.dingar.twok.phae.data.repoImpl.BetRepositoryImpl;
import com.dingar.twok.phae.di.scope.FeatureScope;
import com.dingar.twok.phae.domain.repository.BetRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class BetRepoModule {

    @FeatureScope
    @Binds
    abstract BetRepository provideBetRepository(BetRepositoryImpl betRepository);
}
