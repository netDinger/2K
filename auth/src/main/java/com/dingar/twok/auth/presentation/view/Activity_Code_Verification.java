package com.dingar.twok.auth.presentation.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.dingar.twok.auth.R;
import com.dingar.twok.auth.di.component.VerifyCodeComponent;
import com.dingar.twok.auth.di.component.VerifyCodeComponentProvider;
import com.dingar.twok.auth.presentation.contract.VerifyCode_Contract;
import com.dingar.twok.core.ui.OTPEdittext;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Objects;

import javax.inject.Inject;

public class Activity_Code_Verification extends AppCompatActivity implements VerifyCode_Contract.View {

    @Inject
    VerifyCode_Contract.Presenter presenter;

    Button signup;
    OTPEdittext otpInput;
    TextView resendCode;

    private String phoneNumber, uname, verificationId;
    private PhoneAuthProvider.ForceResendingToken token;

    String TAG = "Activity Code Verification";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_verification);

        //get phone Number from previous activity
        try {
            Bundle data = getIntent().getExtras();
            phoneNumber = data.getString("phone");
            uname = data.getString("uname");
            verificationId = data.getString("verificationId");
            token = data.getParcelable("token");
        } catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());
        }

        //initiate verifyCodeComponent and inject the view
        VerifyCodeComponent verifyCodeComponent = ((VerifyCodeComponentProvider) getApplicationContext())
                .provideVerifyCodeComponent();
        verifyCodeComponent.inject(this);

        //set view to presenter
        presenter.setView(this);
        //set the initial token for resending code. Automatically refresh token by presenter
        presenter.setData(token, verificationId, phoneNumber);

        signup = findViewById(R.id.signup);
        otpInput = findViewById(R.id.otpInput);
        resendCode = findViewById(R.id.resend);

        signup.setOnClickListener(view -> {
            String code = Objects.requireNonNull(otpInput.getText()).toString();
            //signup if code is valid code
            if (presenter.isCodeValid(code))
                presenter.signUp(code);
        });

        resendCode.setOnClickListener(view -> presenter.resendCode());

    }

    //share this activity between login and signup so we need this complicated snippet
    @Override
    public void onVerified() {
        Log.e(TAG, "verified successfully");
        if (uname.isEmpty()) {
            //login account
            Log.e(TAG, "logging in the account");
            toLottery();
        } else    //create account
            presenter.uploadUserData(uname, phoneNumber);
    }

    @Override
    public void onCreateNewUserComplete() {
        toLottery();
    }

    /**
     * intent to @link com.dingar.twok.lottery.presentation.view.Activity_Lottery_Main
     */
    private void toLottery() {
        try {
            Intent intent = new Intent();
            intent.setClassName(this.getPackageName(),
                    "com.dingar.twok.lottery.presentation.view.Activity_Lottery_Main");
            startActivity(intent);
        } catch (Exception exception) {
            Log.e(TAG, exception.getMessage());
        }
    }
}