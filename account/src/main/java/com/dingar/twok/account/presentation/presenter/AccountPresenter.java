package com.dingar.twok.account.presentation.presenter;

import com.dingar.twok.account.presentation.contract.AccountContract;

public class AccountPresenter implements AccountContract.Presenter {
    public AccountPresenter(){}

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

    }
}