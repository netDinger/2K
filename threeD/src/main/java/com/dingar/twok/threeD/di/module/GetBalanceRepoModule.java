package com.dingar.twok.threeD.di.module;

import com.dingar.twok.threeD.data.GetBalanceImpl;
import com.dingar.twok.threeD.domain.repository.GetBalanceRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class GetBalanceRepoModule {
    @Binds
    abstract GetBalanceRepository provideGetBalance(GetBalanceImpl getBalance);
}
