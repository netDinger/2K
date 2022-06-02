package com.dingar.twok.twoK.di.module;

import com.dingar.twok.twoK.data.TimeRemainRepositoryImpl;
import com.dingar.twok.twoK.domain.repository.TimeRemainRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class TimeRemainRepoModule {
    @Binds
    abstract TimeRemainRepository provideTimeRemainRepository(TimeRemainRepositoryImpl timeRemainRepository);
}
