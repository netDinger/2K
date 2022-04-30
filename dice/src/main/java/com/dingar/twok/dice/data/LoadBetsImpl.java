package com.dingar.twok.dice.data;

import com.dingar.twok.dice.data.remoteDataSource.FirebaseLoadBets;
import com.dingar.twok.dice.domain.repository.LoadBets;

import javax.inject.Inject;

import io.reactivex.Observable;

public class LoadBetsImpl implements LoadBets {
    @Inject
    public LoadBetsImpl(){}

    @Override
    public Observable<String> loadBets() {
        return FirebaseLoadBets.getInstance().loadBets();
    }
}
