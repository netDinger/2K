package com.dingar.twok.twoD.di.module;

import com.dingar.twok.twoD.data.repositoryImpl.TimeRemainRepositoryImpl;
import com.dingar.twok.twoD.domain.repository.TimeRemainRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class TimeRemainRepoModule {
    @Binds
    abstract TimeRemainRepository provideTimeRemainRepository(TimeRemainRepositoryImpl timeRemainRepository);
}
