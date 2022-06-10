package com.dingar.twok.dice.domain.repository;

import com.dingar.twok.core.firebase.Result;
import com.dingar.twok.dice.domain.model.LotteryModel;

import java.util.ArrayList;

import io.reactivex.Single;

public interface BetRepository {
    Single<Result> bet(ArrayList<LotteryModel> lotteryModels,double balance);
}
