package com.dingar.twok.threeD.di.module;

import com.dingar.twok.threeD.data.TimeRemainRepositoryImpl;
import com.dingar.twok.threeD.domain.repository.TimeRemainRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class TimeRemainRepoModule {
    @Binds
    abstract TimeRemainRepository provideTimeRemainRepository(TimeRemainRepositoryImpl timeRemainRepository);
}
