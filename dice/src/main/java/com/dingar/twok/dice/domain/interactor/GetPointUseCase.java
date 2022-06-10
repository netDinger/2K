package com.dingar.twok.dice.domain.interactor;

import com.dingar.twok.dice.domain.repository.GetPointRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetPointUseCase {
    @Inject
    public GetPointUseCase(){}

    @Inject
    public GetPointRepository repository;

    public Single<Double> getPoint(){
        return repository.getPoint();
    }
}
