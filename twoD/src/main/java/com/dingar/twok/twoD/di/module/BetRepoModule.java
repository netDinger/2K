package com.dingar.twok.twoD.di.module;


import com.dingar.twok.twoD.data.repositoryImpl.BetRepositoryImpl;
import com.dingar.twok.twoD.domain.repository.BetRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class BetRepoModule {
    @Binds
    abstract BetRepository provideBetRepository(BetRepositoryImpl betRepository);
}
