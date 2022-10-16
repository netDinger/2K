package com.dingar.twok.twoK.presentation.contract;

import com.dingar.twok.core.BasePresenter;
import com.dingar.twok.twoK.data.model.WinLotteryModel;

public interface WinLotteryContract {
    interface View{
        void onLuckyHistoryLoaded(WinLotteryModel model);
        void onCurrentTwoDLoaded();
    }

    interface Presenter extends BasePresenter<View> {
        /**
         * to load all lucky number history for a month*/
        void loadLuckyHistory();
    }
}
