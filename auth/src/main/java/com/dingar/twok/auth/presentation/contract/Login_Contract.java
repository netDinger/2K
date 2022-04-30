package com.dingar.twok.auth.presentation.contract;

import android.content.Context;

import com.dingar.twok.auth.presentation.presenter.BasePresenter;
import com.google.firebase.auth.PhoneAuthProvider;

public interface Login_Contract {
    interface View{
        void showProgress();
        void verifyCode(PhoneAuthProvider.ForceResendingToken token, String verificationId);
    }


    interface Presenter extends BasePresenter<View>{
        void onLoginClick(String phone);
        void onSignupClick(Context context);
        boolean isValidInput(String phone);
    }
}
