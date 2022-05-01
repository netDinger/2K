package com.dingar.twok.dice.di.module;

import com.dingar.twok.dice.data.WinHistoryRepositoryImpl;
import com.dingar.twok.dice.domain.repository.WinHistoryRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class WinLotteryRepoModule {
    @Binds
    abstract WinHistoryRepository provideWinHistoryRepository(WinHistoryRepositoryImpl winHistoryRepository);
}
