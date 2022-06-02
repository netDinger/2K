package com.dingar.twok.history.di.module;

import com.dingar.twok.history.data.repositoryImpl.Get2KBetSlipsRepositoryImpl;
import com.dingar.twok.history.di.scope.FeatureScope;
import com.dingar.twok.history.domain.repository.Get2KBetSlipsRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class Get2KBetSlipRepoModule {
    @FeatureScope
    @Binds
    public abstract Get2KBetSlipsRepository provideRepository(Get2KBetSlipsRepositoryImpl get2KBetSlipsRepository);
}
