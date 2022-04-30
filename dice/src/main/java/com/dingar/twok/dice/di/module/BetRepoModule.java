package com.dingar.twok.dice.di.module;

import com.dingar.twok.dice.data.BetRepositoryImpl;
import com.dingar.twok.dice.domain.repository.BetRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class BetRepoModule {
    @Binds
    abstract BetRepository provideBetRepository(BetRepositoryImpl betRepository);
}
