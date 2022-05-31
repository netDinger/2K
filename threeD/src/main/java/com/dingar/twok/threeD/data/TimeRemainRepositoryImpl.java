package com.dingar.twok.threeD.data;

import com.dingar.twok.threeD.data.remoteDataSource.FirebaseGetTimeRemaining;
import com.dingar.twok.threeD.domain.repository.TimeRemainRepository;

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
