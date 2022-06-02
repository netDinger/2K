package com.dingar.twok.twoK.di.module;

import com.dingar.twok.twoK.data.LoadBetsImpl;
import com.dingar.twok.twoK.domain.repository.LoadBets;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class LoadBetRepoModule {
    @Binds
    abstract LoadBets provideLoadBets(LoadBetsImpl loadBets);
}
