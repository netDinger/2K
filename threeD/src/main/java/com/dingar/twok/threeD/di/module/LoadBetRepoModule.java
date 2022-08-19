package com.dingar.twok.threeD.di.module;

import com.dingar.twok.threeD.data.repoImpl.LoadBetsImpl;
import com.dingar.twok.threeD.domain.repository.LoadBets;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class LoadBetRepoModule {
    @Binds
    abstract LoadBets provideLoadBets(LoadBetsImpl loadBets);
}
