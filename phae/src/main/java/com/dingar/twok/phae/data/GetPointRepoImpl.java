package com.dingar.twok.phae.data;

import com.dingar.twok.firebaseadapter.GetPoint;
import com.dingar.twok.phae.domain.repository.GetPointRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetPointRepoImpl implements GetPointRepository {
    @Inject
    GetPointRepoImpl(){}

    @Override
    public Single<Double> getPoint() {
        return GetPoint.getInstance().getPoints();
    }
}
