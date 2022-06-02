package com.dingar.twok.history.domain.repository;

import com.dingar.twok.history.data.model.BetSlipModel;

import io.reactivex.Observable;

public interface Get3DBetSlipsRepository {
    Observable<BetSlipModel> get3DBetSlips();
}
