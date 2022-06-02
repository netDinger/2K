package com.dingar.twok.twoK.domain.repository;

import io.reactivex.Observable;

/**
 * get the bet able times (lottery opening time) as admin set
 */
public interface BetableTimeRepository {
    Observable<String> getBetAbleTime();
}
