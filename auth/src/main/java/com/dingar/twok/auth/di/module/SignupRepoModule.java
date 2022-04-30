package com.dingar.twok.auth.di.module;

import com.dingar.twok.auth.data.repository.SignupRepositoryImpl;
import com.dingar.twok.auth.domain.repository.SignupRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class SignupRepoModule {
    @Binds
    abstract SignupRepository provideSignupRepository(SignupRepositoryImpl signupRepository);

}
