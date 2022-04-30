package com.dingar.twok.auth.presentation.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.dingar.twok.auth.data.ReqCodeResult;
import com.dingar.twok.auth.domain.interactor.UploadNewUserUseCase;
import com.dingar.twok.auth.domain.interactor.VerifyCodeUseCase;
import com.dingar.twok.auth.presentation.contract.VerifyCode_Contract;
import com.dingar.twok.core.firebase.Result;
import com.google.firebase.auth.PhoneAuthProvider;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class VerifyCodePresenter implements VerifyCode_Contract.Presenter {

    private VerifyCode_Contract.View view;

    VerifyCodeUseCase useCase;
    UploadNewUserUseCase userUseCase;

    private String phoneNumber,verificationId;
    private PhoneAuthProvider.ForceResendingToken token;

    /**
     * provide this dependency by {@link com.dingar.twok.auth.di.module.VerifyCodeModule}
     * @param useCase for code verification
     * @param userUseCase for uploading new user
     *                    since this is just a small project, we don't split presenter
     */
    public VerifyCodePresenter(VerifyCodeUseCase useCase, UploadNewUserUseCase userUseCase){
        this.useCase = useCase;
        this.userUseCase = userUseCase;
    }

    @Override
    public void setView(VerifyCode_Contract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
    }

    //Triggered on Signup button click
    @Override
    public void signUp(@NonNull String code) {
        try {
                phoneNumber = "+95"+phoneNumber.substring(1);
            useCase.execute(phoneNumber,token,verificationId, code)
                    .subscribe(new SingleObserver<Result>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                        }

                        @Override
                        public void onSuccess(@NonNull Result result) {
                            if (result.isSuccess()) {
                                Log.e("VerifyCodePresenter", "execute successfully");
                                //code has verified
                                view.onVerified();
                            }
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {

                        }
                    });
        }catch (NullPointerException exception){
            Log.e("VerifyCodePresenter",exception.getMessage());
        }
    }

    @Override
    public boolean isCodeValid(@NonNull String code) {  //code validity checker
        return !code.isEmpty() && code.length() >= 6;
    }

    @Override
    public void uploadUserData(@NonNull String userName,@NonNull String phoneNumber) {
        Log.e("VerifyCodePresenter",userName);
        userUseCase.execute(userName,phoneNumber)
        .subscribe(new SingleObserver<Result>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull Result result) {
                if (result.isSuccess()){
                    view.onCreateNewUserComplete();
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }

    //initially set all data to verify code
    @Override
    public void setData(PhoneAuthProvider.ForceResendingToken token,
                        String verificationId,
                        String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.verificationId = verificationId;
        this.token = token;
    }

    @Override
    public void resendCode() {
        useCase.resendCode(phoneNumber,token,verificationId)
                .subscribe(new SingleObserver<ReqCodeResult>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull ReqCodeResult reqCodeResult) {
                        if (reqCodeResult.isSuccess()){
                            //renew token and verificationId because of new Code arrival
                            token = reqCodeResult.getForceResendingToken();
                            verificationId = reqCodeResult.getVerificationId();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

}
