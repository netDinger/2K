package com.dingar.twok.account.presentation.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.dingar.twok.account.data.model.User;
import com.dingar.twok.account.domain.interactor.GetBalanceUseCase;
import com.dingar.twok.account.domain.interactor.GetPointUseCase;
import com.dingar.twok.account.domain.interactor.GetUserInfoUseCase;
import com.dingar.twok.account.presentation.contract.AccountContract;
import com.dingar.twok.firebaseadapter.LogOut;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class AccountPresenter implements AccountContract.Presenter {

    private final String TAG = "AccountPresenter";

    public GetBalanceUseCase getBalanceUseCase;
    GetUserInfoUseCase getUserInfoUseCase;
    GetPointUseCase getPointUseCase;

    public AccountPresenter(GetBalanceUseCase getBalanceUseCase,
                            GetUserInfoUseCase getUserInfoUseCase,
                            GetPointUseCase getPointUseCase){
        this.getBalanceUseCase = getBalanceUseCase;
        this.getUserInfoUseCase = getUserInfoUseCase;
        this.getPointUseCase = getPointUseCase;
    }

    private AccountContract.View view;

    @Override
    public void setView(AccountContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        view = null;
    }

    @Override
    public void loadUserBalance() {
      getBalanceUseCase.execute().subscribe(new SingleObserver<Double>() {
          @Override
          public void onSubscribe(@NonNull Disposable d) { }

          @Override
          public void onSuccess(@NonNull Double balance) {
              view.onBalanceLoaded("Balance: "+balance);
          }

          @Override
          public void onError(@NonNull Throwable e) {
              view.showToast(e.getMessage());
              view.onBalanceLoaded("Balance: 0.0K");
          }
      });
    }

    @Override
    public void loadUserPoint() {
        getPointUseCase.getPoints().subscribe(new SingleObserver<Double>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull Double point) {
                view.onPointLoaded("Points: "+point);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                view.showToast(e.getMessage());
                view.onPointLoaded("Points: 0P");
            }
        });
    }

    @Override
    public void logout() {
        //this breaking the clean architecture
        //TODO: replace this snippet with clean architecture
        LogOut.getInstance().logoutUser();
    }

    @Override
    public void loadUserInfo() {
        getUserInfoUseCase.execute().subscribe(new SingleObserver<User>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }
            @Override
            public void onSuccess(@NonNull User user) {
                view.onUserInfoLoaded(user);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                view.showToast(e.getMessage());
            }
        });
    }

}