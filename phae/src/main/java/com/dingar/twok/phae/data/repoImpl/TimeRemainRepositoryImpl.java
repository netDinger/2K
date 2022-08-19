package com.dingar.twok.phae.data.repoImpl;

import com.dingar.twok.phae.data.remoteDataSource.FirebaseGetTimeRemaining;
import com.dingar.twok.phae.domain.repository.TimeRemainRepository;

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
