package com.dingar.twok.threeD.data.repoImpl;


import com.dingar.twok.threeD.data.remoteDataSource.FirebaseGetBetableTimes;
import com.dingar.twok.threeD.domain.repository.BetableTimeRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class BetableTimeRepoImpl implements BetableTimeRepository {

    @Inject
    public BetableTimeRepoImpl(){}

    @Override
    public Observable<String> getBetAbleTime() {
        return FirebaseGetBetableTimes.getInstance().getTime();
    }
}
