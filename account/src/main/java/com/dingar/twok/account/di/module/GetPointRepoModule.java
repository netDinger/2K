package com.dingar.twok.account.di.module;

import com.dingar.twok.account.data.repositoryImpl.GetPointRepositoryImpl;
import com.dingar.twok.account.di.scope.FeatureScope;
import com.dingar.twok.account.domain.repository.GetPointRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class GetPointRepoModule {
    @FeatureScope
    @Binds
    public abstract GetPointRepository provideRepository(GetPointRepositoryImpl getPointRepository);
}
