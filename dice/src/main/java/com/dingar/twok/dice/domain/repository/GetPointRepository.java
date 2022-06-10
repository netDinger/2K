package com.dingar.twok.dice.domain.repository;

import io.reactivex.Single;

public interface GetPointRepository {
    Single<Double> getPoint();
}
