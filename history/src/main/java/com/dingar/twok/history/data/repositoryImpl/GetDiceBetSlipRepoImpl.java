package com.dingar.twok.history.data.repositoryImpl;

import com.dingar.twok.history.data.model.BetSlipModel;
import com.dingar.twok.history.data.remoteDataSource.FirebaseGetDiceBetSlip;
import com.dingar.twok.history.domain.repository.GetDiceBetSlipsRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetDiceBetSlipRepoImpl implements GetDiceBetSlipsRepository {

    @Inject
    public GetDiceBetSlipRepoImpl(){}

    @Override
    public Observable<BetSlipModel> getDiceBetSlips() {
        return FirebaseGetDiceBetSlip.getInstance().getDiceBetSlips();
    }
}
