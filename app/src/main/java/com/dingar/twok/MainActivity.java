package com.dingar.twok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.dingar.twok.auth.domain.interactor.AuthUseCase;
import com.dingar.twok.auth.presentation.view.Activity_Login;
import com.dingar.twok.di.component.AppComponent;
import com.dingar.twok.dice.presentation.view.Activity_DiceBet;
import com.dingar.twok.lottery.presentation.view.Activity_Lottery_Main;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    AuthUseCase authUseCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppComponent appComponent = ((BaseApplication)getApplicationContext())
                .getAppComponent();
        appComponent.inject(this);

        if (authUseCase.isAuthenticated())
            startActivity(new Intent(this, Activity_Lottery_Main.class));
        else
            startActivity(new Intent(this, Activity_Login.class));
        finish();



    }
}