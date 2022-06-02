package com.dingar.twok.phae.domain.repository;

import io.reactivex.Single;

public interface GetBalanceRepository {
    Single<Double> getBalance();
}
