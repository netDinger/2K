package com.dingar.twok.twoK.domain.repository;

import com.dingar.twok.core.firebase.Result;

import io.reactivex.Single;

/**
 * check if the user can still bet right now or closed.
 */
public interface CheckBetableRepository {
    Single<Result> checkBetable(String date);
}
