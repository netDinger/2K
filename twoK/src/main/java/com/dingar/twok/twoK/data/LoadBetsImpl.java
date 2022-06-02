package com.dingar.twok.twoK.data;

import com.dingar.twok.twoK.data.remoteDataSource.FirebaseLoadBets;
import com.dingar.twok.twoK.domain.repository.LoadBets;

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
