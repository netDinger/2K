package com.dingar.twok.twoK.domain.interactor;

import com.dingar.twok.twoK.data.model.WinLotteryModel;
import com.dingar.twok.twoK.domain.repository.WinHistoryRepository;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * execute to get lucky number history (win history)
 */
public class WinHistoryUseCase {

    @Inject
    public WinHistoryRepository winHistoryRepository;

    @Inject
    public WinHistoryUseCase(){}

    public Observable<WinLotteryModel> execute(){
        return winHistoryRepository.loadWinHistory();
    }

    public Single<String> currentTwoD(){return winHistoryRepository.currentTwoD();}
}
