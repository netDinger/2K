package com.dingar.twok.threeD.domain.repository;

import com.dingar.twok.threeD.data.model.WinLotteryModel;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface WinHistoryRepository {
    Observable<WinLotteryModel> loadWinHistory();
    Single<String> currentTwoD();
}
