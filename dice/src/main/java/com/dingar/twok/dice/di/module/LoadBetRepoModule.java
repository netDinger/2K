package com.dingar.twok.dice.di.module;

import com.dingar.twok.dice.data.repositoryImpl.LoadBetsImpl;
import com.dingar.twok.dice.domain.repository.LoadBets;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class LoadBetRepoModule {
    @Binds
    abstract LoadBets provideLoadBets(LoadBetsImpl loadBets);
}
