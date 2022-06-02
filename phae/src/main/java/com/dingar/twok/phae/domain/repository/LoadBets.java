package com.dingar.twok.phae.domain.repository;

import io.reactivex.Observable;

public interface LoadBets {
    Observable<String> loadBets();
}
