package com.dingar.twok.phae.presentation.contract;

import com.dingar.twok.core.BasePresenter;
import com.dingar.twok.phae.data.model.WinLotteryModel;

public interface WinLotteryContract {
    interface View{
        void onLuckyHistoryLoaded(WinLotteryModel model);
        void onCurrentTwoDLoaded(); //just to show the last updated time
    }

    interface Presenter extends BasePresenter<View> {
        /**
         * to load all lucky number history for a month*/
        void loadLuckyHistory();
    }
}
