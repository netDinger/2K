package com.dingar.twok.dice.data;

import com.dingar.twok.dice.domain.repository.GetBalanceRepository;
import com.dingar.twok.firebaseadapter.GetBalance;

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
