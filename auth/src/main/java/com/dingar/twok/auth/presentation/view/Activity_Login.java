package com.dingar.twok.auth.presentation.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.dingar.twok.auth.R;
import com.dingar.twok.auth.di.component.LoginComponent;
import com.dingar.twok.auth.di.component.LoginComponentProvider;
import com.dingar.twok.auth.presentation.contract.Login_Contract;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Objects;

import javax.inject.Inject;

public class Activity_Login extends AppCompatActivity implements Login_Contract.View {

    final String TAG = "Activity_login";

    @Inject
    Login_Contract.Presenter presenter;

    TextInputEditText phone;
    Button login,signup;

    String phoneNumber;

    LoginComponent loginComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initiate the login component and inject the view
        loginComponent = ((LoginComponentProvider)getApplicationContext())
                .provideLoginComponent();
        loginComponent.inject(this);

        //set the view to presenter
        presenter.setView(this);

        setContentView(R.layout.activity_login);

        //declaration the widgets
        phone = findViewById(R.id.inputPhone);

        login = findViewById(R.id.login_btn);
        signup = findViewById(R.id.signup_btn);

        login.setOnClickListener(view ->{
            phoneNumber = Objects.requireNonNull(phone.getText()).toString();
            if (presenter.isValidInput(phoneNumber))
            presenter.onLoginClick(phoneNumber);
                });

        signup.setOnClickListener(view -> presenter.onSignupClick(this));

        //set view to presenter
        presenter.setView(this);
    }

    @Override
    public void onBackPressed(){
        moveTaskToBack(true);
    }

    @Override
    public void showProgress() {

    }
    @Override
    public void verifyCode(PhoneAuthProvider.ForceResendingToken token, String verificationId) {
        Log.e(TAG,"verify code called");
        Intent intent = new Intent(this,Activity_Code_Verification.class);
        intent.putExtra("phone",phoneNumber);
        intent.putExtra("uname","");
        intent.putExtra("token",token);
        intent.putExtra("verificationId",verificationId);
        startActivity(intent);
    }
}