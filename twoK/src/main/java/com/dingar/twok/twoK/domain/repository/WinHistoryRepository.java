package com.dingar.twok.twoK.domain.repository;

import com.dingar.twok.twoK.data.model.WinLotteryModel;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface WinHistoryRepository {
    Observable<WinLotteryModel> loadWinHistory();
    Single<String> currentFormula();
}
