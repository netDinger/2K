package com.dingar.twok.dice.domain.repository;

import io.reactivex.Single;

public interface TimeRemainRepository {
    Single<String> getTimeRemain();
}
