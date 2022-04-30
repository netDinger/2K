package com.dingar.twok.auth.di.module;

import com.dingar.twok.auth.data.repository.LoginRepositoryImpl;
import com.dingar.twok.auth.di.scope.FeatureScope;
import com.dingar.twok.auth.domain.repository.LoginRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class LoginRepoModule {

    @FeatureScope
    @Binds
    abstract LoginRepository provideLoginRepository(LoginRepositoryImpl loginRepository);
}
