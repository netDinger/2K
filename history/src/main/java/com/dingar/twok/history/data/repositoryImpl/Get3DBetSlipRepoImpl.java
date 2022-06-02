package com.dingar.twok.history.data.repositoryImpl;

import com.dingar.twok.history.data.model.BetSlipModel;
import com.dingar.twok.history.data.remoteDataSource.FirebaseGet3DBetSlip;
import com.dingar.twok.history.domain.repository.Get3DBetSlipsRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Repository interface implementation for {@link Get3DBetSlipsRepository}
 *
 */
public class Get3DBetSlipRepoImpl implements Get3DBetSlipsRepository {
    @Inject
    public Get3DBetSlipRepoImpl(){}

    @Override
    public Observable<BetSlipModel> get3DBetSlips() {
        return FirebaseGet3DBetSlip.getInstance().get3DBetSlips();
    }
}
