package com.dingar.twok.phae.data;

import com.dingar.twok.phae.data.remoteDataSource.FirebaseLoadBets;
import com.dingar.twok.phae.domain.repository.LoadBets;

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
