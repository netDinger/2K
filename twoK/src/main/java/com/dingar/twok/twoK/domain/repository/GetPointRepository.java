package com.dingar.twok.twoK.domain.repository;

import io.reactivex.Single;

public interface GetPointRepository {
    Single<Double> getPoint();
}
