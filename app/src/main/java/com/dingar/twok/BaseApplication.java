package com.dingar.twok;

import android.app.Application;

import com.dingar.twok.account.di.component.AccountComponent;
import com.dingar.twok.account.di.component.ComponentProviderAccount;
import com.dingar.twok.auth.di.component.LoginComponent;
import com.dingar.twok.auth.di.component.LoginComponentProvider;
import com.dingar.twok.auth.di.component.SignupComponent;
import com.dingar.twok.auth.di.component.SignupComponentProvider;
import com.dingar.twok.auth.di.component.VerifyCodeComponent;
import com.dingar.twok.auth.di.component.VerifyCodeComponentProvider;
import com.dingar.twok.di.component.AppComponent;
import com.dingar.twok.di.component.DaggerAppComponent;
import com.dingar.twok.dice.di.component.BetSlipComponent;
import com.dingar.twok.dice.di.component.BetSlipComponentProvider;
import com.dingar.twok.dice.di.component.DiceBetComponent;
import com.dingar.twok.dice.di.component.DiceBetComponentProvider;
import com.dingar.twok.dice.di.component.WinLotteryComponent;
import com.dingar.twok.firebaseadapter.FirebaseOffline;


public class BaseApplication extends Application implements LoginComponentProvider,
        SignupComponentProvider,
        VerifyCodeComponentProvider,
        DiceBetComponentProvider,
        BetSlipComponentProvider,
        ComponentProviderAccount

{
    AppComponent appComponent;

    @Override
    public void onCreate()
    {
        super.onCreate();
        //to queue and cache data while offline
        FirebaseOffline.offlineUse();
        initAppComponent();
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent.create(); //builder().build();
    }

    public AppComponent getAppComponent()
    {
        return appComponent;
    }
    
    

    @Override
    public LoginComponent provideLoginComponent() {
       return getAppComponent().loginComponentBuilder().build();
    }

    @Override
    public SignupComponent provideSignupComponent() {
        return getAppComponent().signupComponentBuilder().create();
    }

    @Override
    public VerifyCodeComponent provideVerifyCodeComponent() {
        return getAppComponent().verifyCodeComponentFactory().create();
    }

    @Override
    public DiceBetComponent provideDiceBetComponent() {
        return getAppComponent().diceComponentBuilder().build();
    }

    @Override
    public BetSlipComponent provideBetSlipComponent() {
        return getAppComponent().betSlipComponentBuilder().build();
    }

    @Override
    public WinLotteryComponent provideWinLotteryComponent() {
        return getAppComponent().winLotteryComponentBuilder().build();
    }

    @Override
    public AccountComponent provideAccountComponent() {
        return getAppComponent().accountComponentBuilder().build();
    }
}