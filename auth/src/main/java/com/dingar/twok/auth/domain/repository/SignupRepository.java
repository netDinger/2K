package com.dingar.twok.auth.domain.repository;

import com.dingar.twok.auth.data.ReqCodeResult;
import com.dingar.twok.auth.domain.entity.User;
import com.dingar.twok.core.firebase.Result;

import io.reactivex.Observable;

public interface SignupRepository {
    Observable<ReqCodeResult> signup(User user);
}
