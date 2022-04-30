package com.dingar.twok.auth.data.repository;

import com.dingar.twok.auth.data.RemoteDataSource.FirebaseUploadUser;
import com.dingar.twok.auth.domain.repository.UploadUserRepository;
import com.dingar.twok.core.firebase.Result;

import javax.inject.Inject;

import io.reactivex.Single;

public class UploadUserRepositoryImpl implements UploadUserRepository {

    @Inject
    public UploadUserRepositoryImpl(){}

    @Inject
    public FirebaseUploadUser firebaseUploadUser;

    @Override
    public Single<Result> uploadUser(String userName, String phoneNumber) {
        return firebaseUploadUser.uploadUser(userName,phoneNumber);
    }
}
