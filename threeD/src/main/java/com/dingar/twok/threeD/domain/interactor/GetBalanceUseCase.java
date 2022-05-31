package com.dingar.twok.threeD.domain.interactor;

import com.dingar.twok.threeD.domain.repository.GetBalanceRepository;

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
