package com.dingar.twok.dice.data;

import com.dingar.twok.dice.data.remoteDataSource.FirebaseGetBetableTimes;
import com.dingar.twok.dice.domain.repository.BetableTimeRepository;

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
