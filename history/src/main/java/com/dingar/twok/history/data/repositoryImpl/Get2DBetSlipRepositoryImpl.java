package com.dingar.twok.history.data.repositoryImpl;

import com.dingar.twok.history.data.model.BetSlipModel;
import com.dingar.twok.history.data.remoteDataSource.FirebaseGet2DBetSlip;
import com.dingar.twok.history.domain.repository.Get2DBetSlipsRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Repository interface implementation for {@link Get2DBetSlipsRepository}
 *
 */
public class Get2DBetSlipRepositoryImpl implements Get2DBetSlipsRepository {

    @Inject
    public Get2DBetSlipRepositoryImpl(){}

    @Override
    public Observable<BetSlipModel> get2DBetSlips() {
        return FirebaseGet2DBetSlip.getInstance().get2DBetSlips();
    }
}
