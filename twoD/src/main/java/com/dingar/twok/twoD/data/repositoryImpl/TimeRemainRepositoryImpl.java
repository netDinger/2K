package com.dingar.twok.twoD.data.repositoryImpl;


import com.dingar.twok.twoD.data.remoteDataSource.FirebaseGetTimeRemaining;
import com.dingar.twok.twoD.domain.repository.TimeRemainRepository;

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
