package com.dingar.twok.history.presentation.contract;

import com.dingar.twok.core.BasePresenter;
import com.dingar.twok.history.data.model.BetSlipModel;

public interface HistoryContract {
    interface View{
        void onHistoryLoaded(BetSlipModel betSlipModel);
    }

    interface Presenter extends BasePresenter<View>{
        void loadHistory();
    }
}
