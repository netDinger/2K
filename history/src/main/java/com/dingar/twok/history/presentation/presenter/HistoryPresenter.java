package com.dingar.twok.history.presentation.presenter;

import androidx.annotation.NonNull;

import com.dingar.twok.history.data.model.BetSlipModel;
import com.dingar.twok.history.domain.interactor.GetBetSlipHistoryUseCase;
import com.dingar.twok.history.presentation.contract.HistoryContract;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HistoryPresenter implements HistoryContract.Presenter {

    private  HistoryContract.View view;
    private GetBetSlipHistoryUseCase useCase;

    public HistoryPresenter(GetBetSlipHistoryUseCase useCase){
        this.useCase = useCase;
    }

    @Override
    public void setView(HistoryContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        this.view = null;
    }

    @Override
    public void loadHistory() {
        useCase.execute().subscribe(new Observer<BetSlipModel>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull BetSlipModel betSlipModel) {

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
