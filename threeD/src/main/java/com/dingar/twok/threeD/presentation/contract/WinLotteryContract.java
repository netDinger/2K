package com.dingar.twok.threeD.presentation.contract;

import com.dingar.twok.core.BasePresenter;
import com.dingar.twok.threeD.data.model.WinLotteryModel;

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
