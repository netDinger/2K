package com.dingar.twok.dice.di.module;

import com.dingar.twok.dice.data.TimeRemainRepositoryImpl;
import com.dingar.twok.dice.domain.repository.TimeRemainRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class TimeRemainRepoModule {
    @Binds
    abstract TimeRemainRepository provideTimeRemainRepository(TimeRemainRepositoryImpl timeRemainRepository);
}
