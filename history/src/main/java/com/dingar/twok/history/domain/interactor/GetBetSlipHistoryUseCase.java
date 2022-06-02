package com.dingar.twok.history.domain.interactor;

import com.dingar.twok.history.data.model.BetSlipModel;
import com.dingar.twok.history.domain.repository.Get2DBetSlipsRepository;
import com.dingar.twok.history.domain.repository.Get2KBetSlipsRepository;
import com.dingar.twok.history.domain.repository.Get3DBetSlipsRepository;
import com.dingar.twok.history.domain.repository.GetDiceBetSlipsRepository;
import com.dingar.twok.history.domain.repository.GetPhaeBetSlipRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetBetSlipHistoryUseCase {
    @Inject
    public Get2DBetSlipsRepository get2DBetSlipsRepository;

    @Inject
    public Get2KBetSlipsRepository get2KBetSlipsRepository;

    @Inject
    public Get3DBetSlipsRepository get3DBetSlipsRepository;

    @Inject
    public GetPhaeBetSlipRepository getPhaeBetSlipRepository;

    @Inject
    public GetDiceBetSlipsRepository getDiceBetSlipsRepository;

    @Inject
    public GetBetSlipHistoryUseCase(){}

    public Observable<BetSlipModel> get2DHistory(){
        return get2DBetSlipsRepository.get2DBetSlips();
    }

    public Observable<BetSlipModel> get3DHistory(){
        return get3DBetSlipsRepository.get3DBetSlips();
    }

    public Observable<BetSlipModel> get2KHistory(){
        return get2KBetSlipsRepository.get2KBetSlips();
    }

    public Observable<BetSlipModel> getDiceHistory(){
        return getDiceBetSlipsRepository.getDiceBetSlips();
    }

    public Observable<BetSlipModel> getPhaeHistory(){
        return getPhaeBetSlipRepository.getPhaeBetSlips();
    }
}
