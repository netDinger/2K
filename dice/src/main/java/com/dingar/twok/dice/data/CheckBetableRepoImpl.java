package com.dingar.twok.dice.data;

import com.dingar.twok.core.firebase.Result;
import com.dingar.twok.dice.data.remoteDataSource.FirebaseCheckBetable;
import com.dingar.twok.dice.domain.repository.CheckBetableRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class CheckBetableRepoImpl implements CheckBetableRepository {
    @Inject
    public CheckBetableRepoImpl(){}

    @Override
    public Single<Result> checkBetable(String timestamp) {
        return FirebaseCheckBetable.getInstance().checkBetable(timestamp);
    }
}
