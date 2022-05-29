package com.dingar.twok.di.component;


import com.dingar.twok.MainActivity;
import com.dingar.twok.account.di.component.AccountComponent;
import com.dingar.twok.auth.di.component.LoginComponent;
import com.dingar.twok.auth.di.component.SignupComponent;
import com.dingar.twok.auth.di.component.VerifyCodeComponent;
import com.dingar.twok.auth.di.module.AuthModule;
import com.dingar.twok.di.module.AppModule;
import com.dingar.twok.dice.di.component.DiceBetSlipComponent;
import com.dingar.twok.dice.di.component.DiceBetComponent;
import com.dingar.twok.dice.di.component.DiceWinLotteryComponent;
import com.dingar.twok.history.di.component.HistoryComponent;
import com.dingar.twok.twoD.di.component.TwoDBetComponent;
import com.dingar.twok.twoD.di.component.TwoDBetSlipComponent;
import com.dingar.twok.twoD.di.component.TwoDWinLotteryComponent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, AuthModule.class})
public interface AppComponent {

    LoginComponent.Builder loginComponentBuilder();
    SignupComponent.Factory signupComponentBuilder();
    VerifyCodeComponent.Factory verifyCodeComponentFactory();

    //for dice module
    DiceBetComponent.Builder diceComponentBuilder();
    DiceBetSlipComponent.Builder diceBetSlipComponentBuilder();
    DiceWinLotteryComponent.Builder diceWinLotteryComponentBuilder();

    //for twoD module
    TwoDBetComponent.Builder twoDComponentBuilder();
    TwoDBetSlipComponent.Builder twoDBetSlipComponentBuilder();
    TwoDWinLotteryComponent.Builder twoDWinLotteryComponentBuilder();

    AccountComponent.Builder accountComponentBuilder();
    HistoryComponent.Builder historyComponentBuilder();



    void inject(MainActivity mainActivity);

}

