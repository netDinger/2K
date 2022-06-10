package com.dingar.twok.twoK.di.module;

import com.dingar.twok.twoK.data.GetPointRepoImpl;
import com.dingar.twok.twoK.domain.repository.GetPointRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class GetPointRepoModule {

    @Binds
    public abstract GetPointRepository providePointRepository(GetPointRepoImpl getPointRepo);
}
