package com.dingar.twok.threeD.domain.repository;

import io.reactivex.Single;

/**
 * get the next win time (lottery opening time) to calculate the time remaining
 */
public interface TimeRemainRepository {
    Single<String> getTimeRemain();
}
