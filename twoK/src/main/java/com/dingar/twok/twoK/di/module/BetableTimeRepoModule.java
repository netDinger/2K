package com.dingar.twok.twoK.di.module;

import com.dingar.twok.twoK.data.BetableTimeRepoImpl;
import com.dingar.twok.twoK.di.scope.FeatureScope;
import com.dingar.twok.twoK.domain.repository.BetableTimeRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class BetableTimeRepoModule {

    @FeatureScope
    @Binds
    public abstract BetableTimeRepository provideBetTimeRepo(BetableTimeRepoImpl betableTimeRepo);
}
