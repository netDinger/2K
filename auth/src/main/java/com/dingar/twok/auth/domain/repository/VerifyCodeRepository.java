package com.dingar.twok.auth.domain.repository;

import com.dingar.twok.auth.data.ReqCodeResult;
import com.dingar.twok.core.firebase.Result;
import com.google.firebase.auth.PhoneAuthProvider;

import io.reactivex.Single;

public interface VerifyCodeRepository {
    Single<Result>  verifyWithCode(String phoneNumber,
                                   PhoneAuthProvider.ForceResendingToken token,
                                   String verificationId,
                                   String code);
    Single<ReqCodeResult> resendCode(String phoneNumber,
                                     PhoneAuthProvider.ForceResendingToken token,
                                     String verificationId);
}
