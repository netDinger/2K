package com.dingar.twok.twoD.data.repositoryImpl;

import com.dingar.twok.twoD.data.remoteDataSource.FirebaseGetBetableTimes;
import com.dingar.twok.twoD.domain.repository.BetableTimeRepository;

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
