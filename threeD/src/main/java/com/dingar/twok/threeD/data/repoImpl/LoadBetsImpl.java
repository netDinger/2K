package com.dingar.twok.threeD.data.repoImpl;

import com.dingar.twok.threeD.data.remoteDataSource.FirebaseLoadBets;
import com.dingar.twok.threeD.domain.repository.LoadBets;

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
