package com.dingar.twok.dice.domain.interactor;

import com.dingar.twok.core.firebase.Result;
import com.dingar.twok.dice.domain.model.LotteryModel;
import com.dingar.twok.dice.domain.repository.BetRepository;

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

    public Single<Result> execute(ArrayList<LotteryModel> lotteryModels,double balance){
        return betRepository.bet(lotteryModels,balance);
    }

    public Single<Result> betByPoint(ArrayList<LotteryModel> lotteryModels,double point){
        return betRepository.betByPoint(lotteryModels,point);
    }
}
