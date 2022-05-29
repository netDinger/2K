package com.dingar.twok.twoD.di.module;


import com.dingar.twok.twoD.data.repositoryImpl.LoadBetsImpl;
import com.dingar.twok.twoD.di.scope.FeatureScope;
import com.dingar.twok.twoD.domain.repository.LoadBets;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class LoadBetRepoModule {
    @FeatureScope
    @Binds
    abstract LoadBets provideLoadBets(LoadBetsImpl loadBets);
}
