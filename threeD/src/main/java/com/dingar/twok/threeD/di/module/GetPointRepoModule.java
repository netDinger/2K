package com.dingar.twok.threeD.di.module;

import com.dingar.twok.threeD.data.GetPointRepoImpl;
import com.dingar.twok.threeD.domain.repository.GetPointRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class GetPointRepoModule {

    @Binds
    public abstract GetPointRepository providePointRepository(GetPointRepoImpl getPointRepo);
}
