package com.dingar.twok.account.domain.repository;

import com.dingar.twok.account.data.model.User;

import io.reactivex.Single;

public interface GetUserInfoRepository {
    Single<User> getUserInfo();
}
