package com.dingar.twok.phae.di.module;

import com.dingar.twok.phae.data.repoImpl.BetableTimeRepoImpl;
import com.dingar.twok.phae.di.scope.FeatureScope;
import com.dingar.twok.phae.domain.repository.BetableTimeRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class BetableTimeRepoModule {

    @FeatureScope
    @Binds
    public abstract BetableTimeRepository provideBetTimeRepo(BetableTimeRepoImpl betableTimeRepo);
}
