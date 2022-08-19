package com.dingar.twok.phae.di.module;


import com.dingar.twok.phae.data.repoImpl.TimeRemainRepositoryImpl;
import com.dingar.twok.phae.domain.repository.TimeRemainRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class TimeRemainRepoModule {
    @Binds
    abstract TimeRemainRepository provideTimeRemainRepository(TimeRemainRepositoryImpl timeRemainRepository);
}
