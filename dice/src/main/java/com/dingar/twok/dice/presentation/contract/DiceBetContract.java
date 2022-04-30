package com.dingar.twok.dice.presentation.contract;

import com.dingar.twok.core.BasePresenter;
import com.dingar.twok.dice.domain.model.LotteryModel;

import java.util.ArrayList;

public interface DiceBetContract {
    interface View{
        void onLotteriesLoad(ArrayList<LotteryModel> lotteries);

        /**
         * used by {@link com.dingar.twok.dice.presentation.view.GridRecyclerviewAdapter}
         * when lottery is selected, tell the view(Activity) to add to bets list
         * @param lotteryModel user selected bet slip
         */
        void addBetSlip(LotteryModel lotteryModel);

        /**
         * used by {@link com.dingar.twok.dice.presentation.view.GridRecyclerviewAdapter}
         * when lottery is selected, tell the view(Activity) to remove from bets list
         * @param lotteryModel user unselected bet slip
         */
        void removeBetSlip(LotteryModel lotteryModel);

        void onBalanceLoaded(String balance);
    }

    interface Presenter extends BasePresenter<View> {
        void loadLotteries();

        void onLoadBalance();

        /**
         *
         * @param amount bet amount
         * @return if amount is empty or less than minimum amount
         */
        boolean isStringValid(String amount);
    }

}
