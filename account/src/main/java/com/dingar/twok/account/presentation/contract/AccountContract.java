package com.dingar.twok.account.presentation.contract;

import com.dingar.twok.core.BasePresenter;

public interface AccountContract {

    interface View{
        void onBalanceLoaded(String balance);
    }

    interface Presenter extends BasePresenter<View> {
        void loadUserBalance();
    }

}
