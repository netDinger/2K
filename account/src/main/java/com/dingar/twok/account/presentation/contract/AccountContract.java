package com.dingar.twok.account.presentation.contract;

import com.dingar.twok.account.data.model.User;
import com.dingar.twok.core.BasePresenter;

public interface AccountContract {

    interface View{
        void onBalanceLoaded(String balance);
        void onPointLoaded(String point);
        void onUserInfoLoaded(User user);
    }

    interface Presenter extends BasePresenter<View> {
        void loadUserBalance();

        /**
         * to get the user's current points
         * {@link AccountContract.View#onPointLoaded(String)}
         */
        void loadUserPoint();
        void logout();
        void loadUserInfo();
    }

}
