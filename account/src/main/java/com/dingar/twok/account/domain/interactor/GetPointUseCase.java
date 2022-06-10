package com.dingar.twok.account.domain.interactor;

import com.dingar.twok.account.domain.repository.GetPointRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetPointUseCase {
    @Inject
    public GetPointUseCase(){}

    @Inject
    public GetPointRepository repository;

    public Single<Double> getPoints(){
        return repository.getPoint();
    }
}
