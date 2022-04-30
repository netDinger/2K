package com.dingar.twok.auth.domain.interactor;

import com.dingar.twok.auth.data.ReqCodeResult;
import com.dingar.twok.auth.domain.repository.LoginRepository;
import com.dingar.twok.core.firebase.Result;

import javax.inject.Inject;

import io.reactivex.Single;

public class LoginUsecase {
    private final LoginRepository loginRepository;

    @Inject
    public LoginUsecase(LoginRepository loginRepository){
        this.loginRepository = loginRepository;
    }

    public Single<ReqCodeResult> login(String phone){
      return loginRepository.login(phone);
    }

}
