package com.dingar.twok.threeD.di.module;

import com.dingar.twok.threeD.data.repoImpl.CheckBetableRepoImpl;
import com.dingar.twok.threeD.di.scope.FeatureScope;
import com.dingar.twok.threeD.domain.repository.CheckBetableRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class CheckBetableRepoModule {

    @FeatureScope
    @Binds
    public abstract CheckBetableRepository provideRepository(CheckBetableRepoImpl repoImpl);
}
