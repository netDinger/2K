package com.dingar.twok.auth.presentation.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.dingar.twok.auth.data.ReqCodeResult;
import com.dingar.twok.auth.domain.entity.User;
import com.dingar.twok.auth.domain.interactor.SignupUseCase;
import com.dingar.twok.auth.presentation.contract.SignupContract;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SignupPresenter implements SignupContract.Presenter {

    SignupUseCase signupUseCase;

    public SignupPresenter(SignupUseCase signupUseCase){
        this.signupUseCase = signupUseCase;
    }

    SignupContract.View view;

    @Override
    public void setView(SignupContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {

    }
    /**
     * @param username to send to database if verification success
     */
    @Override
    public void onSignup(String username, @NonNull String phoneNumber) {
        Log.e("SignupPresenter","onSignup called");
        String phone = "+95"+phoneNumber.substring(1);
        view.showProgress();

        //observe onCodeSent state from phone verification
        signupUseCase.executeSignup(new User(username,phone))
                .subscribe(new Observer<ReqCodeResult>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ReqCodeResult reqCodeResult) {
                        //if code sending success
                        if (reqCodeResult.isSuccess()){
                            view.hideProgress();
                            view.verifyCode(reqCodeResult.getForceResendingToken(),
                                    reqCodeResult.getVerificationId());
                            Log.e("SignupPresenter", "Observer onSuccess called");
                        }
                        else
                            Log.e("SignupPresenter","Observer onFailed called");

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }); //end of signupUseCase.executeSignup().subscribe
    }//end of signup()

    @Override
    public boolean isInputValid(String username, String phone) {
        return username != null && !username.isEmpty()&& phone!=null &&!phone.isEmpty();
    }
}