package com.dingar.twok.twoK.domain.repository;

import com.dingar.twok.core.firebase.Result;
import com.dingar.twok.twoK.data.model.LotteryModel;

import java.util.ArrayList;

import io.reactivex.Single;

public interface BetRepository {
    /**
     *
     * @param lotteryModels list of bet slips to bet
     * @param balance user's balance to calculate the new balance(only on client side)
     * @return observer for uploading the bet slip and observe the success result
     */
    Single<Result> bet(ArrayList<LotteryModel> lotteryModels,double balance);


    Single<Result> betByPoint(ArrayList<LotteryModel> lotteryModels,double point);

}
