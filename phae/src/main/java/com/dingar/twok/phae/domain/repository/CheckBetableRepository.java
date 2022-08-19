package com.dingar.twok.phae.domain.repository;

import com.dingar.twok.core.firebase.Result;

import io.reactivex.Single;

public interface CheckBetableRepository {
    Single<Result> checkBetable(String date);
}
