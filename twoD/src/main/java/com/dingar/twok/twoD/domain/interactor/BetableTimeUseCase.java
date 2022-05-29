package com.dingar.twok.twoD.domain.interactor;


import com.dingar.twok.twoD.domain.repository.BetableTimeRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class BetableTimeUseCase {

    @Inject
    public BetableTimeRepository repository;

    @Inject
    public BetableTimeUseCase(){}

    public Observable<String> execute(){
        return repository.getBetAbleTime();
    }
}
