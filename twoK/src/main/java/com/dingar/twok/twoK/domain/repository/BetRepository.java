package com.dingar.twok.twoK.domain.repository;

import com.dingar.twok.core.firebase.Result;
import com.dingar.twok.twoK.data.model.LotteryModel;

import java.util.ArrayList;

import io.reactivex.Single;

public interface BetRepository {
    Single<Result> bet(ArrayList<LotteryModel> lotteryModels);
}
