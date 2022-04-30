package com.dingar.twok.auth.data.repository;

import com.dingar.twok.auth.data.RemoteDataSource.FirebaseSignup;
import com.dingar.twok.auth.data.RemoteDataSource.FirebaseVerifyCode;
import com.dingar.twok.auth.data.ReqCodeResult;
import com.dingar.twok.auth.domain.repository.VerifyCodeRepository;
import com.dingar.twok.core.firebase.Result;
import com.google.firebase.auth.PhoneAuthProvider;

import javax.inject.Inject;

import io.reactivex.Single;

public class VerifyCodeRepositoryImpl implements VerifyCodeRepository {
    @Inject
    public VerifyCodeRepositoryImpl(){}

    @Inject
    public FirebaseVerifyCode firebaseVerifyCode;

    @Override
    public Single<Result> verifyWithCode(String phoneNumber, PhoneAuthProvider.ForceResendingToken token, String verificationId, String code) {
        firebaseVerifyCode.setFieldValues(phoneNumber,token,verificationId);
        return firebaseVerifyCode.verifyCode(code);
    }

    @Override
    public Single<ReqCodeResult> resendCode(String phoneNumber,
                                            PhoneAuthProvider.ForceResendingToken token,
                                            String verificationId) {
        return firebaseVerifyCode.resendCode(phoneNumber,token,verificationId);
    }
}
