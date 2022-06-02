package com.dingar.twok.twoK.data;

import com.dingar.twok.core.firebase.Result;
import com.dingar.twok.twoK.data.model.LotteryModel;
import com.dingar.twok.twoK.data.remoteDataSource.FirebaseBetSlip;
import com.dingar.twok.twoK.domain.repository.BetRepository;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Single;

public class BetRepositoryImpl implements BetRepository {
    @Inject
    public BetRepositoryImpl(){}

    @Override
    public Single<Result> bet(ArrayList<LotteryModel> lotteryModels) {
        return FirebaseBetSlip.getInstance().bet(lotteryModels);
    }
}
