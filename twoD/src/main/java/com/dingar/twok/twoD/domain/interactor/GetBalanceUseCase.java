package com.dingar.twok.twoD.domain.interactor;

import com.dingar.twok.twoD.domain.repository.GetBalanceRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetBalanceUseCase {
    @Inject
    public GetBalanceRepository getBalanceRepository;

    @Inject
    public GetBalanceUseCase(){}

    public Single<Double> execute(){
        return getBalanceRepository.getBalance();
    }
}
