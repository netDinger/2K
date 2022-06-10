package com.dingar.twok.twoK.domain.interactor;

import com.dingar.twok.core.firebase.Result;
import com.dingar.twok.twoK.data.model.LotteryModel;
import com.dingar.twok.twoK.domain.repository.BetRepository;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Bet all the bet slips
 * @author PPK
 */
public class BetUseCase {

    @Inject
    public BetRepository betRepository;

    @Inject
    public BetUseCase(){}

    /**
     *
     * @param lotteryModels list of bet slips to bet
     * @param balance user's balance to calculate the new balance(only on client side)
     * @return observer for uploading the bet slip and observe the success result
     */
    public Single<Result> execute(ArrayList<LotteryModel> lotteryModels,double balance){
        return betRepository.bet(lotteryModels,balance);
    }

    public Single<Result> betByPoint(ArrayList<LotteryModel> lotteryModels,double point){
        return betRepository.betByPoint(lotteryModels,point);
    }
}
