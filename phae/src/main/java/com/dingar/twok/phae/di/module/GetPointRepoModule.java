package com.dingar.twok.phae.di.module;

import com.dingar.twok.phae.data.repoImpl.GetPointRepoImpl;
import com.dingar.twok.phae.domain.repository.GetPointRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class GetPointRepoModule {

    @Binds
    public abstract GetPointRepository providePointRepository(GetPointRepoImpl getPointRepo);
}
