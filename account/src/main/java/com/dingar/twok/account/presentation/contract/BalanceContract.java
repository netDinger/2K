package com.dingar.twok.account.presentation.contract;

import com.dingar.twok.core.BasePresenter;

public interface BalanceContract {
    interface View{

        /**
         * @param balance user's balance
         */
        void onBalanceLoaded(String balance);
    }

    interface Presenter extends BasePresenter<View> {
        /**
         * {@link View#onBalanceLoaded(String)}
         */
        void loadBalance();
    }
}
