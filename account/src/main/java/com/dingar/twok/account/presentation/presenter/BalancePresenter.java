package com.dingar.twok.account.presentation.presenter;

import androidx.annotation.NonNull;

import com.dingar.twok.account.domain.interactor.GetBalanceUseCase;
import com.dingar.twok.account.domain.interactor.GetOTPUseCase;
import com.dingar.twok.account.presentation.contract.BalanceContract;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class BalancePresenter implements BalanceContract.Presenter {

    private final GetBalanceUseCase getBalanceUseCase;
    private final GetOTPUseCase getOTPUseCase;

    public BalancePresenter(GetBalanceUseCase getBalanceUseCase,GetOTPUseCase getOTPUseCase){
        this.getBalanceUseCase = getBalanceUseCase;
        this.getOTPUseCase = getOTPUseCase;
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
    public void loadOTP() {
        getOTPUseCase.getOtp().subscribe(new SingleObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {}

            @Override
            public void onSuccess(String s) {
                view.onOTPLoaded(s);
            }

            @Override
            public void onError(Throwable e) {

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
