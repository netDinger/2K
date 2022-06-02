package com.dingar.twok.phae.domain.interactor;


import com.dingar.twok.core.firebase.Result;
import com.dingar.twok.phae.data.model.LotteryModel;
import com.dingar.twok.phae.domain.repository.BetRepository;

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

    public Single<Result> execute(ArrayList<LotteryModel> lotteryModels){
        return betRepository.bet(lotteryModels);
    }
}
