package com.dingar.twok.threeD.domain.repository;

import io.reactivex.Single;

public interface GetPointRepository {
    Single<Double> getPoint();
}
