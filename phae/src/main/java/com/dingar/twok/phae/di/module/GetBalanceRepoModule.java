package com.dingar.twok.phae.di.module;

import com.dingar.twok.phae.data.repoImpl.GetBalanceImpl;
import com.dingar.twok.phae.domain.repository.GetBalanceRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class GetBalanceRepoModule {
    @Binds
    abstract GetBalanceRepository provideGetBalance(GetBalanceImpl getBalance);
}
