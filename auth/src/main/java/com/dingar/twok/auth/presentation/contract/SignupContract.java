package com.dingar.twok.auth.presentation.contract;

import com.dingar.twok.auth.presentation.presenter.BasePresenter;
import com.google.firebase.auth.PhoneAuthProvider;

public interface SignupContract {
    interface View{
        void showProgress();
        void hideProgress();

        /**
         *
         * @param token token for resending new SMS if code not sent or timeout.
         * @param verificationId verification ID for SMS verification
         *                       sending new SMS code will generate new params and handled by
         * {@link com.dingar.twok.auth.presentation.view.Activity_Code_Verification}
         */
        void verifyCode(PhoneAuthProvider.ForceResendingToken token,String verificationId);
    }

    interface Presenter extends BasePresenter<View>{
        void onSignup(String username, String phoneNumber);
        boolean isInputValid(String username,String phone);
    }
}
