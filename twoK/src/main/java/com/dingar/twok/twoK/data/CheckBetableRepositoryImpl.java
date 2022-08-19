package com.dingar.twok.twoK.data;

import com.dingar.twok.core.firebase.Result;
import com.dingar.twok.twoK.data.remoteDataSource.FirebaseCheckBetable;
import com.dingar.twok.twoK.domain.repository.CheckBetableRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class CheckBetableRepositoryImpl implements CheckBetableRepository {
    @Inject
    public CheckBetableRepositoryImpl(){}

    @Override
    public Single<Result> checkBetable(String date) {
        return FirebaseCheckBetable.getInstance().checkBetable(date);
    }
}
