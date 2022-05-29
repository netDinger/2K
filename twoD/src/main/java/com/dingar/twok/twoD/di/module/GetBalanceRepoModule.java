package com.dingar.twok.twoD.di.module;


import com.dingar.twok.twoD.data.repositoryImpl.GetBalanceImpl;
import com.dingar.twok.twoD.domain.repository.GetBalanceRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class GetBalanceRepoModule {
    @Binds
    abstract GetBalanceRepository provideGetBalance(GetBalanceImpl getBalance);
}
