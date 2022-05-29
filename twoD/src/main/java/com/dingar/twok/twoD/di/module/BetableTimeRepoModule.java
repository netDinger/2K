package com.dingar.twok.twoD.di.module;


import com.dingar.twok.twoD.data.repositoryImpl.BetableTimeRepoImpl;
import com.dingar.twok.twoD.di.scope.FeatureScope;
import com.dingar.twok.twoD.domain.repository.BetableTimeRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class BetableTimeRepoModule {

    @FeatureScope
    @Binds
    public abstract BetableTimeRepository provideBetTimeRepo(BetableTimeRepoImpl betableTimeRepo);
}
