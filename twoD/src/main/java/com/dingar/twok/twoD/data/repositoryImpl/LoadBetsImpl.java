package com.dingar.twok.twoD.data.repositoryImpl;


import com.dingar.twok.twoD.data.remoteDataSource.FirebaseLoadBets;
import com.dingar.twok.twoD.domain.repository.LoadBets;

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
