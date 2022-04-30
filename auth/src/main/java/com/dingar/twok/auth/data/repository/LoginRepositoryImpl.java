package com.dingar.twok.auth.data.repository;

import com.dingar.twok.auth.data.RemoteDataSource.FirebaseLogin;
import com.dingar.twok.auth.data.ReqCodeResult;
import com.dingar.twok.auth.domain.repository.LoginRepository;
import com.dingar.twok.core.firebase.Result;

import javax.inject.Inject;

import io.reactivex.Single;

public class LoginRepositoryImpl implements LoginRepository {

    @Inject
    public LoginRepositoryImpl(){}

    @Inject
    public FirebaseLogin firebaseLogin;

    @Override
    public Single<ReqCodeResult> login(String phoneNumber) {
     return firebaseLogin.login(phoneNumber);
    }
}
