package com.dingar.twok.threeD.domain.repository;

import io.reactivex.Observable;

public interface LoadBets {
    Observable<String> loadBets();
}
