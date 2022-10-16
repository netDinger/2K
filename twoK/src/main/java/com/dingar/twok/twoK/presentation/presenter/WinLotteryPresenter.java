package com.dingar.twok.twoK.presentation.presenter;

import androidx.annotation.NonNull;

import com.dingar.twok.twoK.data.model.WinLotteryModel;
import com.dingar.twok.twoK.domain.interactor.WinHistoryUseCase;
import com.dingar.twok.twoK.presentation.contract.WinLotteryContract;

import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * {@link com.dingar.twok.twoK.presentation.view.Activity_Win_Lotteries}
 * @see WinLotteryContract
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
    }
}