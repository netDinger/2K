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
import com.dingar.twok.dice.di.component.DiceBetSlipComponent;
import com.dingar.twok.dice.di.component.ComponentProviderDice;
import com.dingar.twok.dice.di.component.DiceBetComponent;
import com.dingar.twok.dice.di.component.DiceWinLotteryComponent;
import com.dingar.twok.firebaseadapter.FirebaseOffline;
import com.dingar.twok.history.di.component.ComponentProviderHistory;
import com.dingar.twok.history.di.component.HistoryComponent;
import com.dingar.twok.phae.di.component.ComponentProviderPhae;
import com.dingar.twok.phae.di.component.PhaeBetComponent;
import com.dingar.twok.phae.di.component.PhaeBetSlipComponent;
import com.dingar.twok.phae.di.component.PhaeWinLotteryComponent;
import com.dingar.twok.threeD.di.component.ComponentProviderThreeD;
import com.dingar.twok.threeD.di.component.ThreeDBetComponent;
import com.dingar.twok.threeD.di.component.ThreeDBetSlipComponent;
import com.dingar.twok.threeD.di.component.ThreeDWinLotteryComponent;
import com.dingar.twok.twoD.di.component.ComponentProviderTwoD;
import com.dingar.twok.twoD.di.component.TwoDBetComponent;
import com.dingar.twok.twoD.di.component.TwoDBetSlipComponent;
import com.dingar.twok.twoD.di.component.TwoDWinLotteryComponent;
import com.dingar.twok.twoK.di.component.ComponentProviderTwoK;
import com.dingar.twok.twoK.di.component.TwoKBetComponent;
import com.dingar.twok.twoK.di.component.TwoKBetSlipComponent;
import com.dingar.twok.twoK.di.component.TwoKWinLotteryComponent;

/**
 * base application also provide application component to modules
 */
public class BaseApplication extends Application implements LoginComponentProvider,
        SignupComponentProvider,       //TODO: replace with new component provider for whole module
        VerifyCodeComponentProvider,
        ComponentProviderDice,//for dice module
        ComponentProviderAccount,   //for account module
        ComponentProviderHistory,    //for history module
        ComponentProviderTwoD,      //for twoD module
        ComponentProviderThreeD,    //for threeD module
        ComponentProviderPhae,
        ComponentProviderTwoK

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
    public AccountComponent provideAccountComponent() {
        return getAppComponent().accountComponentBuilder().build();
    }

    @Override
    public HistoryComponent provideHistoryComponent() {
        return getAppComponent().historyComponentBuilder().build();
    }

    @Override
    public DiceBetSlipComponent provideBetSlipComponent() {
        return getAppComponent().diceBetSlipComponentBuilder().build();
    }

    @Override
    public DiceWinLotteryComponent provideWinLotteryComponent() {
        return getAppComponent().diceWinLotteryComponentBuilder().build();
    }

    @Override
    public DiceBetComponent provideDiceBetComponent() {
        return getAppComponent().diceComponentBuilder().build();
    }

    @Override
    public TwoDBetSlipComponent provideTwoDBetSlipComponent() {
        return getAppComponent().twoDBetSlipComponentBuilder().build();
    }

    @Override
    public TwoDWinLotteryComponent provideTwoDWinLotteryComponent() {
        return getAppComponent().twoDWinLotteryComponentBuilder().build();
    }

    @Override
    public TwoDBetComponent provideTwoDBetComponent() {
        return getAppComponent().twoDComponentBuilder().build();
    }


    @Override
    public ThreeDBetSlipComponent provideThreeDBetSlipComponent() {
        return getAppComponent().threeDBetSlipComponentBuilder().build();
    }

    @Override
    public ThreeDWinLotteryComponent provideThreeDWinLotteryComponent() {
        return getAppComponent().threeDWinLotteryComponentBuilder().build();
    }

    @Override
    public ThreeDBetComponent provideThreeDBetComponent() {
        return getAppComponent().threeDComponentBuilder().build();
    }

    @Override
    public PhaeBetSlipComponent providePhaeBetSlipComponent() {
        return getAppComponent().phaeBetSlipComponentBuilder().build();
    }

    @Override
    public PhaeWinLotteryComponent providePhaeWinLotteryComponent() {
        return getAppComponent().phaeWinLotteryComponentBuilder().build();
    }

    @Override
    public PhaeBetComponent providePhaeBetComponent() {
        return getAppComponent().phaeComponentBuilder().build();
    }

    @Override
    public TwoKBetSlipComponent provideTwoKBetSlipComponent() {
        return getAppComponent().twoKBetSlipComponentBuilder().build();
    }

    @Override
    public TwoKWinLotteryComponent provideTwoKWinLotteryComponent() {
        return getAppComponent().twoKWinLotteryComponentBuilder().build();
    }

    @Override
    public TwoKBetComponent provideTwoKBetComponent() {
        return getAppComponent().twoKComponentBuilder().build();
    }
}