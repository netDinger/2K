package com.dingar.twok.auth.data.RemoteDataSource;

import android.util.Log;

import androidx.annotation.NonNull;

import com.dingar.twok.auth.data.ReqCodeResult;
import com.dingar.twok.auth.presentation.view.Activity_Code_Verification;
import com.dingar.twok.core.firebase.Result;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;

public class FirebaseVerifyCode {

    String TAG = "FirebaseVerifyCode";
    private String phoneNumber;
    private String verificationId;
    private PhoneAuthProvider.ForceResendingToken token;

    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Inject
    public FirebaseVerifyCode(){}

    public void setFieldValues(String phoneNumber,
                              PhoneAuthProvider.ForceResendingToken token,
                              String verificationId){
        this.phoneNumber = phoneNumber;
        this.token = token;
        this.verificationId = verificationId;
    }

    public Single<Result> verifyCode(@NonNull String code){

        return Single.create(emitter -> {try {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
            signInWithPhoneAuthCredential(emitter,credential);
        }catch (NullPointerException exception){
            exception.printStackTrace();
        }

        });
    }


    private void signInWithPhoneAuthCredential(SingleEmitter<Result> emitter, PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.e("FirebaseSignup","signInWithPhoneAuthCredential task is successful");
                        // Sign in success, update UI with the signed-in user's information
                        emitter.onSuccess(new Result(true));
                        // Update UI
                    } else {
                        // Sign in failed, display a message and update the UI
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException)
                            // The verification code entered was invalid
                            Log.e(TAG,"code invalid");
                    }
                });
    }


    public Single<ReqCodeResult> resendCode(String phoneNumber,
                                            PhoneAuthProvider.ForceResendingToken token,
                                            String verificationId){
        return Single.create(emitter -> {

            PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;

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
                    ReqCodeResult result = new ReqCodeResult();
                    result.setSuccess(true);
                    result.setVerificationId(verificationId);
                    result.setForceResendingToken(forceResendingToken);
                    emitter.onSuccess(result);
                    Log.e(TAG,"onCode Sent called");
                }
            };

            PhoneAuthOptions phoneAuthOptions = PhoneAuthOptions.newBuilder(firebaseAuth)
                    .setPhoneNumber(phoneNumber)
                    .setTimeout(60L, TimeUnit.SECONDS)
                    .setCallbacks(mCallback)
                    .setForceResendingToken(token)
                    .setActivity(new Activity_Code_Verification())
                    .build();

            PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions);

        });

    }


}
