package com.dingar.twok.threeD.di.module;

import com.dingar.twok.threeD.data.repoImpl.BetableTimeRepoImpl;
import com.dingar.twok.threeD.di.scope.FeatureScope;
import com.dingar.twok.threeD.domain.repository.BetableTimeRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class BetableTimeRepoModule {

    @FeatureScope
    @Binds
    public abstract BetableTimeRepository provideBetTimeRepo(BetableTimeRepoImpl betableTimeRepo);
}
