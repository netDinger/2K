package com.dingar.twok.dice.data;

import com.dingar.twok.dice.data.model.WinLotteryModel;
import com.dingar.twok.dice.data.remoteDataSource.FirebaseGetWinHistory;
import com.dingar.twok.dice.data.remoteDataSource.Get_Current_TwoD;
import com.dingar.twok.dice.domain.repository.WinHistoryRepository;

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
