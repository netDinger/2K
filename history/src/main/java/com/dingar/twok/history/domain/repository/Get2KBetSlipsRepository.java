package com.dingar.twok.history.domain.repository;

import com.dingar.twok.history.data.model.BetSlipModel;

import io.reactivex.Observable;

public interface Get2KBetSlipsRepository {
    Observable<BetSlipModel> get2KBetSlips();
}
