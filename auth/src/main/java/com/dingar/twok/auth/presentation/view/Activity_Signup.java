package com.dingar.twok.auth.presentation.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.dingar.twok.auth.R;
import com.dingar.twok.auth.di.component.SignupComponent;
import com.dingar.twok.auth.di.component.SignupComponentProvider;
import com.dingar.twok.auth.presentation.contract.SignupContract;

import com.google.firebase.auth.PhoneAuthProvider;

import javax.inject.Inject;

public class Activity_Signup extends AppCompatActivity implements SignupContract.View {

    @Inject
    SignupContract.Presenter presenter;

    SignupComponent signupComponent;

    EditText username,phone;

    String phoneNumber,uname;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Initiate Signup Subcomponent
        signupComponent = ((SignupComponentProvider) getApplicationContext())
                .provideSignupComponent();
        //Inject the view
        signupComponent.inject(this);

        setContentView(R.layout.activity_signup);

        //set the view to presenter
        presenter.setView(this);

        username = findViewById(R.id.userName);
        phone = findViewById(R.id.phoneNumber);
        signup = findViewById(R.id.signup);

        signup.setOnClickListener(view ->{
            this.phoneNumber =  phone.getText().toString();
            this.uname = username.getText().toString();

            //if username and phone number are not empty
            if (presenter.isInputValid(uname,phoneNumber))

                presenter.onSignup(username.getText().toString(),
                                 phone.getText().toString());
        });
    }

    @Override
    public void onBackPressed(){finish();}

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void verifyCode(PhoneAuthProvider.ForceResendingToken token, String verificationId) {
        Log.e("Activity Signup","verify code called");
        Intent intent = new Intent(this,Activity_Code_Verification.class);
        intent.putExtra("phone",phoneNumber);
        intent.putExtra("uname",uname);
        intent.putExtra("token",token);
        intent.putExtra("verificationId",verificationId);
        startActivity(intent);
    }
}