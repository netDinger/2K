package com.dingar.twok.twoK.data;

import com.dingar.twok.twoK.data.model.WinLotteryModel;
import com.dingar.twok.twoK.data.remoteDataSource.FirebaseGetWinHistory;
import com.dingar.twok.twoK.data.remoteDataSource.Get_Current_TwoK_Formula;
import com.dingar.twok.twoK.domain.repository.WinHistoryRepository;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

public class WinHistoryRepositoryImpl implements WinHistoryRepository {

    @Inject
    public WinHistoryRepositoryImpl(){}

    @Override
    public Observable<WinLotteryModel> loadWinHistory() {
        return FirebaseGetWinHistory.getInstance().getWinHistory();
    }

    @Override
    public Single<String> currentTwoD() {
        return Get_Current_TwoK_Formula.getInstance().getResult();
    }


}
