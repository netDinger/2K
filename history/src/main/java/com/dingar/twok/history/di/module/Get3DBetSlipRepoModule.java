package com.dingar.twok.history.di.module;

import com.dingar.twok.history.data.repositoryImpl.Get3DBetSlipRepoImpl;
import com.dingar.twok.history.di.scope.FeatureScope;
import com.dingar.twok.history.domain.repository.Get3DBetSlipsRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class Get3DBetSlipRepoModule {

    @FeatureScope
    @Binds
    public abstract Get3DBetSlipsRepository provide3DBetSlipRepo(Get3DBetSlipRepoImpl get3DBetSlipRepo);
}
