package com.dingar.twok.phae.domain.interactor;

import com.dingar.twok.core.firebase.Result;
import com.dingar.twok.phae.domain.repository.CheckBetableRepository;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * check if the user can still bet to teh specific win date or not.
 */
public class CheckBetableUseCase {
    @Inject
    public CheckBetableUseCase(){}

    @Inject
    public CheckBetableRepository repository;

    public Single<Result> checkBetable(String date){
        return repository.checkBetable(date);
    }
}
