package com.dingar.twok.threeD.di.module;


import com.dingar.twok.threeD.data.repoImpl.WinHistoryRepositoryImpl;
import com.dingar.twok.threeD.domain.repository.WinHistoryRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class WinLotteryRepoModule {
    @Binds
    abstract WinHistoryRepository provideWinHistoryRepository(WinHistoryRepositoryImpl winHistoryRepository);
}
