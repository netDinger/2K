package com.dingar.twok.twoD.di.module;


import com.dingar.twok.twoD.data.repositoryImpl.WinHistoryRepositoryImpl;
import com.dingar.twok.twoD.domain.repository.WinHistoryRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class WinLotteryRepoModule {
    @Binds
    abstract WinHistoryRepository provideWinHistoryRepository(WinHistoryRepositoryImpl winHistoryRepository);
}
