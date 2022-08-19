package com.dingar.twok.twoK.domain.interactor;

import com.dingar.twok.core.firebase.Result;
import com.dingar.twok.twoK.domain.repository.CheckBetableRepository;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * check if the user can still bet right now or closed.
 */
public class CheckBetableUseCase {
    @Inject
    public CheckBetableUseCase(){}

    @Inject
    public CheckBetableRepository repository;

    public Single<Result> checkBetable(String date){return repository.checkBetable(date);}
}
