package com.dingar.twok.phae.data.repoImpl;

import com.dingar.twok.core.firebase.Result;
import com.dingar.twok.phae.data.model.LotteryModel;
import com.dingar.twok.phae.data.remoteDataSource.FirebaseBetSlip;
import com.dingar.twok.phae.domain.repository.BetRepository;


import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Single;

public class BetRepositoryImpl implements BetRepository {
    @Inject
    public BetRepositoryImpl(){}

    @Override
    public Single<Result> bet(ArrayList<LotteryModel> lotteryModels,double balance) {
        return FirebaseBetSlip.getInstance().bet(lotteryModels,balance);
    }

    @Override
    public Single<Result> betByPoint(ArrayList<LotteryModel> lotteryModels, double point) {
        return FirebaseBetSlip.getInstance().betByPoint(lotteryModels,point);
    }
}
