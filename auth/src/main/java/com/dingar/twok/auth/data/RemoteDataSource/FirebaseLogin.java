package com.dingar.twok.auth.data.RemoteDataSource;

import android.util.Log;

import androidx.annotation.NonNull;

import com.dingar.twok.auth.data.ReqCodeResult;
import com.dingar.twok.auth.presentation.view.Activity_Login;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Single;

public class FirebaseLogin {

    String TAG = "FirebaseLogin";
    @Inject
    public FirebaseLogin(){}

    public FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;

    public Single<ReqCodeResult> login(@NonNull String phone){
        Log.e("FirebaseLogin", "sinup called");
        return Single.create(emitter -> {
            ReqCodeResult result = new ReqCodeResult();

            mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                }
                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {

                }

                @Override
                public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
                    super.onCodeAutoRetrievalTimeOut(s);
                }

                @Override
                public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(verificationId, forceResendingToken);
                    Log.e(TAG,"onCode Sent called");
                    result.setSuccess(true);
                    result.setForceResendingToken(forceResendingToken);
                    result.setVerificationId(verificationId);
                    emitter.onSuccess(result);
                }
            };

            phoneAuth(phone);

        });
    }

    private void phoneAuth(String phoneNumber){
        PhoneAuthOptions phoneAuthOptions = PhoneAuthOptions.newBuilder(firebaseAuth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setCallbacks(mCallback)
                .setActivity(new Activity_Login())
                .build();
        PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions);

    }

}
