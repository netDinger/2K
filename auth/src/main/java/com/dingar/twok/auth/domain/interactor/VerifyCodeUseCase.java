package com.dingar.twok.auth.domain.interactor;

import com.dingar.twok.auth.data.ReqCodeResult;
import com.dingar.twok.auth.domain.repository.VerifyCodeRepository;
import com.dingar.twok.core.firebase.Result;
import com.google.firebase.auth.PhoneAuthProvider;

import javax.inject.Inject;

import io.reactivex.Single;

public class VerifyCodeUseCase {

    VerifyCodeRepository repository;

    @Inject
    public VerifyCodeUseCase(VerifyCodeRepository repository){this.repository = repository;}

    public Single<Result> execute(String phoneNumber,
                                  PhoneAuthProvider.ForceResendingToken token,
                                  String verificationId,
                                  String code){
        return repository.verifyWithCode(phoneNumber,token,verificationId,code);
    }

    public Single<ReqCodeResult> resendCode(String phoneNumber,
                                            PhoneAuthProvider.ForceResendingToken token,
                                            String verificationId){
        return repository.resendCode(phoneNumber,token,verificationId);
    }
}
