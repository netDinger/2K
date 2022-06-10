package com.dingar.twok.account.data.repositoryImpl;

import com.dingar.twok.account.domain.repository.GetPointRepository;
import com.dingar.twok.firebaseadapter.GetPoint;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetPointRepositoryImpl implements GetPointRepository {
    @Inject
    public GetPointRepositoryImpl(){}

    @Override
    public Single<Double> getPoint() {
        return GetPoint.getInstance().getPoints();
    }
}
