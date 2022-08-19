package com.dingar.twok.phae.data.repoImpl;


import com.dingar.twok.phae.data.remoteDataSource.FirebaseGetBetableTimes;
import com.dingar.twok.phae.domain.repository.BetableTimeRepository;

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
