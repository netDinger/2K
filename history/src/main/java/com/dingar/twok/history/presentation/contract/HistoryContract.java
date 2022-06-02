package com.dingar.twok.history.presentation.contract;

import com.dingar.twok.core.BasePresenter;
import com.dingar.twok.history.data.model.BetSlipModel;

public interface HistoryContract {
    interface View{
        void onHistoryLoaded(BetSlipModel betSlipModel);
    }

    interface Presenter extends BasePresenter<View>{
        /**
         * load 2d bet slip history
         */
        void load2DHistory();

        /**
         * load 2K bet slip history
         */
        void load2KHistory();

        /**
         * load 3D bet slip history
         */
        void load3DHistory();

        /**
         * load Phae bet slip history
         */
        void loadPhaeHistory();

        /**
         * load Dice bet slip history
         */
        void loadDiceHistory();
    }
}
