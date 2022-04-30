package com.dingar.twok.di.module;

import android.app.Application;
import android.content.Context;

import com.dingar.twok.BaseApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule
{
    private final BaseApplication application;

    public AppModule(BaseApplication app)
    {
        application = app;
    }

    @Provides
    @Singleton
    Context provideContext()
    {
        return application;
    }

    @Provides
    Application provideApplication()
    {
        return application;
    }
}
