package com.dingar.twok.account.domain.interactor;

import com.dingar.twok.account.domain.repository.GetOTPRepository;

import io.reactivex.Observable;
import javax.inject.Inject;

import io.reactivex.Single;

public class GetOTPUseCase {
    @Inject
    public GetOTPUseCase(){};

    @Inject
    public GetOTPRepository repository;

    public Observable<String> getOtp(){return repository.getOTP();}
}
