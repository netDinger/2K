package com.dingar.twok.history.data.repositoryImpl;

import com.dingar.twok.history.data.model.BetSlipModel;
import com.dingar.twok.history.data.remoteDataSource.FirebaseGetPhaeBetSlip;
import com.dingar.twok.history.domain.repository.GetPhaeBetSlipRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetPhaeBetSlipRepoImpl implements GetPhaeBetSlipRepository {

    @Inject
    public GetPhaeBetSlipRepoImpl(){}

    @Override
    public Observable<BetSlipModel> getPhaeBetSlips() {
        return FirebaseGetPhaeBetSlip.getInstance().getPhaeBetSlips();
    }
}
