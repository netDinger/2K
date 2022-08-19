package com.dingar.twok.twoD.data.repositoryImpl;

import com.dingar.twok.core.firebase.Result;
import com.dingar.twok.twoD.data.remoteDataSource.FirebaseCheckBetable;
import com.dingar.twok.twoD.domain.repository.CheckBetableRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class CheckBetableRepoImpl implements CheckBetableRepository {
    @Inject
    public CheckBetableRepoImpl(){}

    @Override
    public Single<Result> checkBetable(String date) {
        return FirebaseCheckBetable.getInstance().checkBetable(date);
    }
}
