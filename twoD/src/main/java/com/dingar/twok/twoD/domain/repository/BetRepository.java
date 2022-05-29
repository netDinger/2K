package com.dingar.twok.twoD.domain.repository;

import com.dingar.twok.core.firebase.Result;
import com.dingar.twok.twoD.data.model.LotteryModel;

import java.util.ArrayList;

import io.reactivex.Single;

public interface BetRepository {
    Single<Result> bet(ArrayList<LotteryModel> lotteryModels);
}
