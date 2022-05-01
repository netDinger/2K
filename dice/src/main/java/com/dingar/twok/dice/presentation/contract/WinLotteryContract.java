package com.dingar.twok.dice.presentation.contract;

import com.dingar.twok.core.BasePresenter;
import com.dingar.twok.dice.data.model.WinLotteryModel;

public interface WinLotteryContract {
    interface View{
        void onLuckyHistoryLoaded(WinLotteryModel model);
    }

    interface Presenter extends BasePresenter<View> {
        /**
         * to load all lucky number history for a month*/
        void loadLuckyHistory();
    }
}
