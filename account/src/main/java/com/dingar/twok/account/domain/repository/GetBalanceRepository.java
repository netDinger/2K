package com.dingar.twok.account.domain.repository;

import io.reactivex.Single;

public interface GetBalanceRepository {
    Single<Double> getBalance();
}
