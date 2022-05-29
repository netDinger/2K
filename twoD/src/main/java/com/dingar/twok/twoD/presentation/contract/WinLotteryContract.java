package com.dingar.twok.twoD.presentation.contract;

import com.dingar.twok.core.BasePresenter;
import com.dingar.twok.twoD.data.model.WinLotteryModel;

public interface WinLotteryContract {
    interface View{
        void onLuckyHistoryLoaded(WinLotteryModel model);
        void onCurrentTwoDLoaded(String twoD);
    }

    interface Presenter extends BasePresenter<View> {
        /**
         * to load all lucky number history for a month*/
        void loadLuckyHistory();
    }
}
