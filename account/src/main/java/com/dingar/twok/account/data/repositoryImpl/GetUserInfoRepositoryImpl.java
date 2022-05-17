package com.dingar.twok.account.data.repositoryImpl;

import com.dingar.twok.account.data.model.User;
import com.dingar.twok.account.data.remoteDataSource.FirebaseUserInfo;
import com.dingar.twok.account.domain.repository.GetUserInfoRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetUserInfoRepositoryImpl implements GetUserInfoRepository {
    @Inject
    public GetUserInfoRepositoryImpl(){}

    @Override
    public Single<User> getUserInfo() {
        return FirebaseUserInfo.getInstance().getUserInfo();
    }
}
