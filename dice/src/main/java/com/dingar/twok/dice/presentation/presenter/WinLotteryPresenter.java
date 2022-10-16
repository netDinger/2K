package com.dingar.twok.dice.presentation.presenter;

import androidx.annotation.NonNull;

import com.dingar.twok.dice.data.model.WinLotteryModel;
import com.dingar.twok.dice.domain.interactor.WinHistoryUseCase;
import com.dingar.twok.dice.presentation.contract.WinLotteryContract;

import io.reactivex.Observer;

import io.reactivex.disposables.Disposable;

/**
 * {@link com.dingar.twok.dice.presentation.view.Activity_Win_Lotteries}
 * {@link WinLotteryContract}
 */
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
            @Override public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull WinLotteryModel winLotteryModel) {
                view.onLuckyHistoryLoaded(winLotteryModel);
            }

            @Override
            public void onError(@NonNull Throwable e) {
            }

            @Override
            public void onComplete() {
                view.onCurrentTwoDLoaded();
            }
        });

        //load current twoD result
//      winHistoryUseCase.currentTwoD().subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new SingleObserver<String>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {}
//
//            @Override
//            public void onSuccess(@NonNull String twoD) {
//                view.onCurrentTwoDLoaded(twoD);
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                e.printStackTrace();
//                view.onCurrentTwoDLoaded("--");
//            }
//        });

    }
}