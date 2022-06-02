package com.dingar.twok.phae.di.module;

import com.dingar.twok.phae.data.LoadBetsImpl;
import com.dingar.twok.phae.domain.repository.LoadBets;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class LoadBetRepoModule {
    @Binds
    abstract LoadBets provideLoadBets(LoadBetsImpl loadBets);
}
