package com.dingar.twok.twoK.domain.interactor;

import com.dingar.twok.twoK.domain.repository.LoadBets;

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
