package com.dingar.twok.threeD.data;

import com.dingar.twok.core.firebase.Result;

import com.dingar.twok.threeD.data.model.LotteryModel;
import com.dingar.twok.threeD.data.remoteDataSource.FirebaseBetSlip;
import com.dingar.twok.threeD.domain.repository.BetRepository;

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
}
