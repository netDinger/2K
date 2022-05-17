package com.dingar.twok.account.domain.interactor;

import com.dingar.twok.account.data.model.User;
import com.dingar.twok.account.domain.repository.GetUserInfoRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetUserInfoUseCase {
    @Inject
    public GetUserInfoRepository getUserInfoRepository;

    @Inject
    public GetUserInfoUseCase(){}

    public Single<User> execute(){
        return getUserInfoRepository.getUserInfo();
    }
}
