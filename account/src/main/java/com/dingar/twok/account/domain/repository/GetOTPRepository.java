package com.dingar.twok.account.domain.repository;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface GetOTPRepository {
    Observable<String> getOTP();
}
