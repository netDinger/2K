package com.dingar.twok.di.component;


import com.dingar.twok.MainActivity;
import com.dingar.twok.account.di.component.AccountComponent;
import com.dingar.twok.auth.di.component.LoginComponent;
import com.dingar.twok.auth.di.component.SignupComponent;
import com.dingar.twok.auth.di.component.VerifyCodeComponent;
import com.dingar.twok.auth.di.module.AuthModule;
import com.dingar.twok.di.module.AppModule;
import com.dingar.twok.dice.di.component.BetSlipComponent;
import com.dingar.twok.dice.di.component.DiceBetComponent;
import com.dingar.twok.dice.di.component.WinLotteryComponent;
import com.dingar.twok.history.di.component.HistoryComponent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, AuthModule.class})
public interface AppComponent {

    LoginComponent.Builder loginComponentBuilder();
    SignupComponent.Factory signupComponentBuilder();
    VerifyCodeComponent.Factory verifyCodeComponentFactory();
    DiceBetComponent.Builder diceComponentBuilder();
    BetSlipComponent.Builder betSlipComponentBuilder();
    WinLotteryComponent.Builder winLotteryComponentBuilder();
    AccountComponent.Builder accountComponentBuilder();
    HistoryComponent.Builder historyComponentBuilder();

    void inject(MainActivity mainActivity);

}

