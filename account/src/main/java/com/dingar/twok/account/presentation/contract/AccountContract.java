package com.dingar.twok.account.presentation.contract;

import com.dingar.twok.account.data.model.User;
import com.dingar.twok.core.BasePresenter;

public interface AccountContract {

    interface View{
        void onBalanceLoaded(String balance);
        void onUserInfoLoaded(User user);
    }

    interface Presenter extends BasePresenter<View> {
        void loadUserBalance();
        void logout();
        void loadUserInfo();
    }

}
