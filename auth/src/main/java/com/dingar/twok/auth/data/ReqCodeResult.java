package com.dingar.twok.auth.data;

import com.google.firebase.auth.PhoneAuthProvider;

/**
 * @author Pyae Phyo Kyaw
 * As the Firebase Phone Auth updates, clean Architecture for Phone Auth won't be pure.
 */
public class ReqCodeResult {
    private boolean Success;
    private PhoneAuthProvider.ForceResendingToken forceResendingToken;
    private String verificationId;

    public ReqCodeResult(){}
    public ReqCodeResult(boolean success) {
        Success = success;
    }


    public boolean isSuccess() {
        return Success;
    }
    public void setSuccess(boolean success) {
        Success = success;
    }

    public PhoneAuthProvider.ForceResendingToken getForceResendingToken() {
        return forceResendingToken;
    }

    public void setForceResendingToken(PhoneAuthProvider.ForceResendingToken forceResendingToken) {
        this.forceResendingToken = forceResendingToken;
    }

    public String getVerificationId() {
        return verificationId;
    }

    public void setVerificationId(String verificationId) {
        this.verificationId = verificationId;
    }

}
