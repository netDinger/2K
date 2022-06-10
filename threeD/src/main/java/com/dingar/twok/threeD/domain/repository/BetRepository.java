package com.dingar.twok.threeD.domain.repository;

import com.dingar.twok.core.firebase.Result;
import com.dingar.twok.threeD.data.model.LotteryModel;

import java.util.ArrayList;

import io.reactivex.Single;

public interface BetRepository {
    Single<Result> bet(ArrayList<LotteryModel> lotteryModels,double balance);
}
