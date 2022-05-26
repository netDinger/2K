package com.dingar.twok.dice.di.module;

import com.dingar.twok.dice.data.BetableTimeRepoImpl;
import com.dingar.twok.dice.di.scope.FeatureScope;
import com.dingar.twok.dice.domain.repository.BetableTimeRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class BetableTimeRepoModule {

    @FeatureScope
    @Binds
    public abstract BetableTimeRepository provideBetTimeRepo(BetableTimeRepoImpl betableTimeRepo);
}
