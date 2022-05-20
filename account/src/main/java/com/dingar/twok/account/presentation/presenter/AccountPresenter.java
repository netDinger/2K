package com.dingar.twok.account.presentation.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.dingar.twok.account.data.model.User;
import com.dingar.twok.account.domain.interactor.GetBalanceUseCase;
import com.dingar.twok.account.domain.interactor.GetUserInfoUseCase;
import com.dingar.twok.account.presentation.contract.AccountContract;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class AccountPresenter implements AccountContract.Presenter {

    public GetBalanceUseCase getBalanceUseCase;
    private GetUserInfoUseCase getUserInfoUseCase;

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
              view.onBalanceLoaded(String.valueOf(balance));
          }

          @Override
          public void onError(@NonNull Throwable e) {
              view.onBalanceLoaded("No Balance");
          }
      });
    }

    @Override
    public void logout() { }

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