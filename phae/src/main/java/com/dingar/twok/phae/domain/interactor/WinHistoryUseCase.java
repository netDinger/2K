package com.dingar.twok.phae.domain.interactor;

import com.dingar.twok.phae.data.model.WinLotteryModel;
import com.dingar.twok.phae.domain.repository.WinHistoryRepository;

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
}
