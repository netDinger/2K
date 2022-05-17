package com.dingar.twok.account.data.repositoryImpl;

import com.dingar.twok.account.domain.repository.GetBalanceRepository;
import com.dingar.twok.firebaseadapter.GetBalance;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetBalanceRepositoryImpl implements GetBalanceRepository {

    @Inject
    GetBalanceRepositoryImpl(){}

    @Override
    public Single<Double> getBalance() {
        return GetBalance.getInstance().gtBalance();
    }
}
