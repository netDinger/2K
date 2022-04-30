package com.dingar.twok.auth.domain.repository;


import com.dingar.twok.auth.data.ReqCodeResult;
import com.dingar.twok.core.firebase.Result;

import io.reactivex.Single;

public interface LoginRepository {
    Single<ReqCodeResult> login(String phoneNumber);
}
