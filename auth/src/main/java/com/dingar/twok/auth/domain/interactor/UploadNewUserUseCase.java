package com.dingar.twok.auth.domain.interactor;

import com.dingar.twok.auth.domain.repository.UploadUserRepository;
import com.dingar.twok.core.firebase.Result;

import javax.inject.Inject;

import io.reactivex.Single;

public class UploadNewUserUseCase {

    UploadUserRepository uploadUserRepository;

    @Inject
    public UploadNewUserUseCase(UploadUserRepository uploadUserRepository){
        this.uploadUserRepository = uploadUserRepository;
    }

    public Single<Result> execute(String userName,String phoneNumber){
     return uploadUserRepository.uploadUser(userName,phoneNumber);
    }
}
