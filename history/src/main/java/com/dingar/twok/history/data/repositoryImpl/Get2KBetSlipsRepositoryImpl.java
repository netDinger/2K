package com.dingar.twok.history.data.repositoryImpl;

import com.dingar.twok.history.data.model.BetSlipModel;
import com.dingar.twok.history.data.remoteDataSource.FirebaseGetTwoKBetSlip;
import com.dingar.twok.history.domain.repository.Get2KBetSlipsRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Repository interface implementation for {@link Get2KBetSlipsRepository}
 *
 */
public class Get2KBetSlipsRepositoryImpl implements Get2KBetSlipsRepository {

    @Inject
    public Get2KBetSlipsRepositoryImpl(){}

    @Override
    public Observable<BetSlipModel> get2KBetSlips() {
        return FirebaseGetTwoKBetSlip.getInstance().getTwoKBetSlips();
    }
}
