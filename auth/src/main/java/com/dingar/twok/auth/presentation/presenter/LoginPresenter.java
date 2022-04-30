package com.dingar.twok.auth.presentation.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dingar.twok.auth.data.ReqCodeResult;
import com.dingar.twok.auth.domain.interactor.LoginUsecase;
import com.dingar.twok.auth.presentation.contract.Login_Contract;
import com.dingar.twok.auth.presentation.view.Activity_Signup;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class LoginPresenter implements Login_Contract.Presenter {

    Login_Contract.View view;
    LoginUsecase loginUsecase;

    public LoginPresenter(LoginUsecase loginUsecase){this.loginUsecase = loginUsecase;}

    @Override
    public void onLoginClick(@NonNull String phoneNumber) {
        String phone = "+95"+phoneNumber.substring(1);

        //observe login result
        loginUsecase.login(phone)
        .subscribe(new SingleObserver<ReqCodeResult>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            @Override
            public void onSuccess(@NonNull ReqCodeResult reqCodeResult) {
                if (reqCodeResult.isSuccess()){
                    Log.e("Login Presenter","Login use case execute successfully");
                view.verifyCode(reqCodeResult.getForceResendingToken(),
                        reqCodeResult.getVerificationId());
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });


    }

    @Override
    public void onSignupClick(Context context) {
        context.startActivity(new Intent(context, Activity_Signup.class));
    }

    @Override
    public boolean isValidInput(String phone) {
        return phone!=null&& !phone.isEmpty();
    }

    @Override
    public void setView(Login_Contract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {}

}
