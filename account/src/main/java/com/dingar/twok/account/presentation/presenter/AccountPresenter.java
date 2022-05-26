package com.dingar.twok.account.presentation.presenter;

import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dingar.twok.account.data.model.User;
import com.dingar.twok.account.domain.interactor.GetBalanceUseCase;
import com.dingar.twok.account.domain.interactor.GetUserInfoUseCase;
import com.dingar.twok.account.presentation.contract.AccountContract;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class AccountPresenter implements AccountContract.Presenter {

    public GetBalanceUseCase getBalanceUseCase;
    GetUserInfoUseCase getUserInfoUseCase;

    public AccountPresenter(GetBalanceUseCase getBalanceUseCase,GetUserInfoUseCase getUserInfoUseCase){
        this.getBalanceUseCase = getBalanceUseCase;
        this.getUserInfoUseCase = getUserInfoUseCase;
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
              Log.e("error balance",e.getMessage());
              view.onBalanceLoaded("Balance: 0.0K");
          }
      });
    }

    @Override
    public void logout() {}

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
                Log.e("Account Presenter",e.getMessage());
            }
        });
    }

}