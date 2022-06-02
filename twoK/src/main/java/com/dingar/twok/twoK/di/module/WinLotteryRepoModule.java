package com.dingar.twok.twoK.di.module;

import com.dingar.twok.twoK.data.WinHistoryRepositoryImpl;
import com.dingar.twok.twoK.domain.repository.WinHistoryRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class WinLotteryRepoModule {
    @Binds
    abstract WinHistoryRepository provideWinHistoryRepository(WinHistoryRepositoryImpl winHistoryRepository);
}
