package com.dingar.twok.twoK.data;

import com.dingar.twok.twoK.data.remoteDataSource.FirebaseGetBetableTimes;
import com.dingar.twok.twoK.domain.repository.BetableTimeRepository;

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
