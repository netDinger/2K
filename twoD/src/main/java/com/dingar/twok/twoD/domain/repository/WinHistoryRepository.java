package com.dingar.twok.twoD.domain.repository;

import com.dingar.twok.twoD.data.model.WinLotteryModel;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface WinHistoryRepository {
    Observable<WinLotteryModel> loadWinHistory();
    Single<String> currentTwoD();
}
