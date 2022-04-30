package com.dingar.twok.auth.di.module;

import com.dingar.twok.auth.data.repository.UploadUserRepositoryImpl;
import com.dingar.twok.auth.domain.repository.UploadUserRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class UploadUserRepoModule {
   @Binds
   abstract UploadUserRepository provideUploadUserRepository(UploadUserRepositoryImpl uploadUserRepository);
}
