package com.dingar.twok.threeD.data;

import com.dingar.twok.firebaseadapter.GetPoint;
import com.dingar.twok.threeD.domain.repository.GetPointRepository;

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
