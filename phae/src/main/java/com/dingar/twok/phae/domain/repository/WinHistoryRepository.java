package com.dingar.twok.phae.domain.repository;

import com.dingar.twok.phae.data.model.WinLotteryModel;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface WinHistoryRepository {
    Observable<WinLotteryModel> loadWinHistory();
}
