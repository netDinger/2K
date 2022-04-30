package com.dingar.twok.auth.domain.repository;

import com.dingar.twok.core.firebase.Result;

import io.reactivex.Single;

public interface UploadUserRepository {
    Single<Result> uploadUser(String userName,String phoneNumber);
}
