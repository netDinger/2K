package com.dingar.twok.phae.data.repoImpl;

import com.dingar.twok.phae.data.model.WinLotteryModel;
import com.dingar.twok.phae.data.remoteDataSource.FirebaseGetWinHistory;
import com.dingar.twok.phae.domain.repository.WinHistoryRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class WinHistoryRepositoryImpl implements WinHistoryRepository {

    @Inject
    public WinHistoryRepositoryImpl(){}

    @Override
    public Observable<WinLotteryModel> loadWinHistory() {
        return FirebaseGetWinHistory.getInstance().getWinHistory();
    }

}
