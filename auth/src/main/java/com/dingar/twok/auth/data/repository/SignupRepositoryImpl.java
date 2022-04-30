package com.dingar.twok.auth.data.repository;

import android.util.Log;

import com.dingar.twok.auth.data.RemoteDataSource.FirebaseSignup;
import com.dingar.twok.auth.data.ReqCodeResult;
import com.dingar.twok.auth.domain.entity.User;
import com.dingar.twok.auth.domain.repository.SignupRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class SignupRepositoryImpl implements SignupRepository {

    @Inject
    public SignupRepositoryImpl(){}

    @Inject
    public FirebaseSignup firebaseSignup;

    @Override
    public Observable<ReqCodeResult> signup(User user) {
        Log.e("SignupRepoImpl","signup called");
        return firebaseSignup.signup(user);
    }
}
