package com.dingar.twok.dice.data.repositoryImpl;

import com.dingar.twok.dice.data.remoteDataSource.FirebaseGetTimeRemaining;
import com.dingar.twok.dice.domain.repository.TimeRemainRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class TimeRemainRepositoryImpl implements TimeRemainRepository {

    @Inject
    public TimeRemainRepositoryImpl(){}

    @Override
    public Single<String> getTimeRemain() {
        return FirebaseGetTimeRemaining.getInstance().getTimeRemaining();
    }
}
