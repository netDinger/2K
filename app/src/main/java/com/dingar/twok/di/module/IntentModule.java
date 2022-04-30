package com.dingar.twok.di.module;

import com.dingar.twok.core.IntentRoute;
import com.dingar.twok.lottery.di.LotteryIntentProvider;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class IntentModule {
    @Binds
    abstract IntentRoute provideIntentRoute(LotteryIntentProvider lotteryIntentProvider);
}
