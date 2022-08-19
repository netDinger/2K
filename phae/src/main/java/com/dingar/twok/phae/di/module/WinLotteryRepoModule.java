package com.dingar.twok.phae.di.module;

import com.dingar.twok.phae.data.repoImpl.WinHistoryRepositoryImpl;
import com.dingar.twok.phae.domain.repository.WinHistoryRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class WinLotteryRepoModule {
    @Binds
    abstract WinHistoryRepository provideWinHistoryRepository(WinHistoryRepositoryImpl winHistoryRepository);
}
