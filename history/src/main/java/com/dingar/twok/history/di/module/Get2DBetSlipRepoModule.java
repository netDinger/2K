package com.dingar.twok.history.di.module;

import com.dingar.twok.history.data.repositoryImpl.Get2DBetSlipRepositoryImpl;
import com.dingar.twok.history.di.scope.FeatureScope;
import com.dingar.twok.history.domain.repository.Get2DBetSlipsRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class Get2DBetSlipRepoModule {

    @FeatureScope
    @Binds
    public abstract Get2DBetSlipsRepository provide2DBetSlipRepo(Get2DBetSlipRepositoryImpl get2DBetSlipRepository);
}
