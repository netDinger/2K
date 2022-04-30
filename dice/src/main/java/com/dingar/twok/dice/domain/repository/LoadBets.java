package com.dingar.twok.dice.domain.repository;

import io.reactivex.Observable;

public interface LoadBets {
    Observable<String> loadBets();
}
