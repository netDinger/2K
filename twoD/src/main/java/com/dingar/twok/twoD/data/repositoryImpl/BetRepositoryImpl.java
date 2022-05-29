package com.dingar.twok.twoD.data.repositoryImpl;

import com.dingar.twok.core.firebase.Result;
import com.dingar.twok.twoD.data.model.LotteryModel;
import com.dingar.twok.twoD.data.remoteDataSource.FirebaseBetSlip;
import com.dingar.twok.twoD.domain.repository.BetRepository;

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
