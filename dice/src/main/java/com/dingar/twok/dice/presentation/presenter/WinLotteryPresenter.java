package com.dingar.twok.dice.presentation.presenter;

import androidx.annotation.NonNull;

import com.dingar.twok.dice.data.model.WinLotteryModel;
import com.dingar.twok.dice.domain.interactor.WinHistoryUseCase;
import com.dingar.twok.dice.presentation.contract.WinLotteryContract;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class WinLotteryPresenter implements WinLotteryContract.Presenter {

    WinLotteryContract.View view;
    WinHistoryUseCase winHistoryUseCase;


    public WinLotteryPresenter(WinHistoryUseCase winHistoryUseCase){
        this.winHistoryUseCase = winHistoryUseCase;
    }

    @Override
    public void setView(WinLotteryContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        this.view = null;
    }



    @Override
    public void loadLuckyHistory() {
        winHistoryUseCase.execute().subscribe(new Observer<WinLotteryModel>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull WinLotteryModel winLotteryModel) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
