package com.dingar.twok.phae.domain.interactor;

import com.dingar.twok.phae.domain.repository.TimeRemainRepository;

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
