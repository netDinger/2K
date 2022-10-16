package com.dingar.twok.dice.data.repositoryImpl;

import com.dingar.twok.dice.domain.repository.GetPointRepository;
import com.dingar.twok.firebaseadapter.GetPoint;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetPointRepoImpl implements GetPointRepository {
    @Inject
    public GetPointRepoImpl(){}

    @Override
    public Single<Double> getPoint() {
        return GetPoint.getInstance().getPoints();
    }
}
