package com.dingar.twok.threeD.data.repoImpl;

import com.dingar.twok.threeD.data.model.WinLotteryModel;
import com.dingar.twok.threeD.data.remoteDataSource.FirebaseGetWinHistory;
import com.dingar.twok.threeD.data.remoteDataSource.Get_Current_TwoD;
import com.dingar.twok.threeD.domain.repository.WinHistoryRepository;

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
        return Get_Current_TwoD.getInstance().getResult();
    }


}
