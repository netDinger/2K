package com.dingar.twok.auth.presentation.contract;

import androidx.annotation.NonNull;

import com.dingar.twok.auth.presentation.presenter.BasePresenter;
import com.google.firebase.auth.PhoneAuthProvider;

public interface VerifyCode_Contract {

    interface View{
        void onVerified();
        void onCreateNewUserComplete();
    }

    interface Presenter extends BasePresenter<View> {
        void signUp(@NonNull String code);
        boolean isCodeValid(@NonNull String code);
        void uploadUserData(@NonNull String userName,@NonNull String phoneNumber);

        /**
         *
         * @param token and
         * @param verificationId are from
         * {@link com.dingar.twok.auth.presentation.view.Activity_Signup}
         *              and {@link com.dingar.twok.auth.presentation.view.Activity_Login}
         *                       for resending code and after resending code, those two will be changed
         */
        void setData(PhoneAuthProvider.ForceResendingToken token,
                     String verificationId,String phoneNumber);
        void resendCode();
    }

}
