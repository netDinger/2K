package com.dingar.twok.twoD.domain.repository;

import com.dingar.twok.core.firebase.Result;

import io.reactivex.Single;

/**
 * to check if the user can still bet to the specific win date or not
 *
 */
public interface CheckBetableRepository {
    Single<Result> checkBetable(String date);
}
