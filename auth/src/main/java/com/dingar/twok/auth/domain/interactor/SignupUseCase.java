package com.dingar.twok.auth.domain.interactor;

import android.util.Log;

import com.dingar.twok.auth.data.ReqCodeResult;
import com.dingar.twok.auth.domain.entity.User;
import com.dingar.twok.auth.domain.repository.SignupRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class SignupUseCase {

    private final SignupRepository signupRepository;

    @Inject
    public SignupUseCase(SignupRepository signupRepository){
        this.signupRepository = signupRepository;
    }

    public Observable<ReqCodeResult> executeSignup(User user){
        Log.e("SignupUseCase","executeSignup called");
        return signupRepository.signup(user);
    }
}
