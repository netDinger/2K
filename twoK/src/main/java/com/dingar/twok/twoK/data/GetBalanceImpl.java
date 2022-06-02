package com.dingar.twok.twoK.data;

import com.dingar.twok.firebaseadapter.GetBalance;
import com.dingar.twok.twoK.domain.repository.GetBalanceRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetBalanceImpl implements GetBalanceRepository {

    @Inject
    public GetBalanceImpl(){}

    @Override
    public Single<Double> getBalance() {
        return GetBalance.getInstance().gtBalance();
    }
}
