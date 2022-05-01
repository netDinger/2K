package com.dingar.twok.dice.domain.repository;

import com.dingar.twok.dice.data.model.WinLotteryModel;

import io.reactivex.Observable;

public interface WinHistoryRepository {
    Observable<WinLotteryModel> loadWinHistory();
}
