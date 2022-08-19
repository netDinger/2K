package com.dingar.twok.dice.domain.interactor;

import com.dingar.twok.core.firebase.Result;
import com.dingar.twok.dice.domain.repository.CheckBetableRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class CheckBetableUseCase {
    @Inject
    public CheckBetableUseCase(){}

    @Inject
    public CheckBetableRepository repository;

    public Single<Result> checkBetable(String timestamp){
        return repository.checkBetable(timestamp);
    }
}
