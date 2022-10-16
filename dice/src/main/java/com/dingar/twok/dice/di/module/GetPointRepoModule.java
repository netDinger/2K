package com.dingar.twok.dice.di.module;

import com.dingar.twok.dice.data.repositoryImpl.GetPointRepoImpl;
import com.dingar.twok.dice.domain.repository.GetPointRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class GetPointRepoModule {

    @Binds
    public abstract GetPointRepository providePointRepository(GetPointRepoImpl getPointRepo);
}