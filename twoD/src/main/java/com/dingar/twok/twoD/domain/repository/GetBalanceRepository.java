package com.dingar.twok.twoD.domain.repository;

import io.reactivex.Single;

public interface GetBalanceRepository {
    Single<Double> getBalance();
}
