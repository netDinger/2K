package com.dingar.twok.threeD.domain.interactor;

import com.dingar.twok.threeD.domain.repository.TimeRemainRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class CountDownUseCase {

    @Inject
    public TimeRemainRepository timeRemainRepository;

    @Inject
    public CountDownUseCase(){}

    public Single<String> execute(){
        return timeRemainRepository.getTimeRemain();
    }
}
