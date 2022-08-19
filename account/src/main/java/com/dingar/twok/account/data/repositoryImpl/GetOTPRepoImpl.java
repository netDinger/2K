package com.dingar.twok.account.data.repositoryImpl;

import com.dingar.twok.account.data.remoteDataSource.FirebaseGetOPT;
import com.dingar.twok.account.domain.repository.GetOTPRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetOTPRepoImpl implements GetOTPRepository {
    @Inject
    public GetOTPRepoImpl(){}

    @Override
    public Single<String> getOTP() {
        return FirebaseGetOPT.getInstance().getOpt();
    }
}
