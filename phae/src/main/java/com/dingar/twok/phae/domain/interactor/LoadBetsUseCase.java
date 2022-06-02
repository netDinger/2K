package com.dingar.twok.phae.domain.interactor;

import com.dingar.twok.phae.domain.repository.LoadBets;

import javax.inject.Inject;

import io.reactivex.Observable;

public class LoadBetsUseCase {
    @Inject
    LoadBets loadBets;

    @Inject
    public LoadBetsUseCase(){}

    public Observable<String> execute(){
        return loadBets.loadBets();
    }
}
