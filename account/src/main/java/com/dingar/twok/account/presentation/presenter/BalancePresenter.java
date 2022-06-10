package com.dingar.twok.account.presentation.presenter;

import androidx.annotation.NonNull;

import com.dingar.twok.account.domain.interactor.GetBalanceUseCase;
import com.dingar.twok.account.presentation.contract.BalanceContract;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class BalancePresenter implements BalanceContract.Presenter {

    private final GetBalanceUseCase getBalanceUseCase;

    public BalancePresenter(GetBalanceUseCase getBalanceUseCase){
        this.getBalanceUseCase = getBalanceUseCase;
    }

    BalanceContract.View view;

    @Override
    public void loadBalance() {
        getBalanceUseCase.execute().subscribe(new SingleObserver<Double>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull Double balance) {
                view.onBalanceLoaded(String.valueOf(balance));
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }

    @Override
    public void setView(BalanceContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        this.view = null;
    }
}
