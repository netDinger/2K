package com.dingar.twok.threeD.domain.interactor;

import com.dingar.twok.core.firebase.Result;
import com.dingar.twok.threeD.domain.repository.CheckBetableRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class CheckBetableUseCase {
    @Inject
    public CheckBetableUseCase(){}

    @Inject
    public CheckBetableRepository repository;

    public Single<Result> checkBetable(String date){
        return repository.checkBetable(date);
    }
}
