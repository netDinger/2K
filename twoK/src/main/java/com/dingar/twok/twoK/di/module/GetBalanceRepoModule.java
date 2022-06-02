package com.dingar.twok.twoK.di.module;

import com.dingar.twok.twoK.data.GetBalanceImpl;
import com.dingar.twok.twoK.domain.repository.GetBalanceRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class GetBalanceRepoModule {
    @Binds
    abstract GetBalanceRepository provideGetBalance(GetBalanceImpl getBalance);
}
