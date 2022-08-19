package com.dingar.twok.account.domain.interactor;

import com.dingar.twok.account.domain.repository.GetOTPRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetOTPUseCase {
    @Inject
    public GetOTPUseCase(){};

    @Inject
    public GetOTPRepository repository;

    public Single<String> getOtp(){return repository.getOTP();}
}
