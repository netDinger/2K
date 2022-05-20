package com.dingar.twok.history.domain.interactor;

import com.dingar.twok.history.data.model.BetSlipModel;
import com.dingar.twok.history.domain.repository.Get2DBetSlipsRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetBetSlipHistoryUseCase {
    @Inject
    public Get2DBetSlipsRepository get2DBetSlipsRepository;

    @Inject
    public GetBetSlipHistoryUseCase(){}

    public Observable<BetSlipModel> execute(){
        return get2DBetSlipsRepository.get2DBetSlips();
    }
}
