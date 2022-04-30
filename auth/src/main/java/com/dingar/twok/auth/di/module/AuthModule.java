package com.dingar.twok.auth.di.module;

import com.dingar.twok.auth.data.repository.AuthRepositoryImpl;
import com.dingar.twok.auth.domain.repository.AuthRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class AuthModule {

    @Binds
    abstract AuthRepository authRepositoryImpl(AuthRepositoryImpl authRepository);

}
