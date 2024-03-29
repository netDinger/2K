package com.dingar.twok.dice.di.module;

import com.dingar.twok.dice.data.repositoryImpl.GetBalanceImpl;
import com.dingar.twok.dice.domain.repository.GetBalanceRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class GetBalanceRepoModule {
    @Binds
    abstract GetBalanceRepository provideGetBalance(GetBalanceImpl getBalance);
}
