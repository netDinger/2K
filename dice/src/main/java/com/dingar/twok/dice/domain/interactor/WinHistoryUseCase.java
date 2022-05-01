package com.dingar.twok.dice.domain.interactor;

import com.dingar.twok.dice.data.model.WinLotteryModel;
import com.dingar.twok.dice.domain.repository.WinHistoryRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class WinHistoryUseCase {

    @Inject
    public WinHistoryRepository winHistoryRepository;

    @Inject
    public WinHistoryUseCase(){}

    public Observable<WinLotteryModel> execute(){
        return winHistoryRepository.loadWinHistory();
    }
}
