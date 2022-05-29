package com.dingar.twok.twoD.domain.repository;

import io.reactivex.Observable;

public interface LoadBets {
    Observable<String> loadBets();
}
