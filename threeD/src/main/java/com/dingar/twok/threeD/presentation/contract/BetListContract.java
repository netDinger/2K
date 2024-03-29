package com.dingar.twok.threeD.presentation.contract;

import com.dingar.twok.core.BasePresenter;
import com.dingar.twok.core.BaseView;
import com.dingar.twok.threeD.data.model.LotteryModel;
import com.dingar.twok.threeD.presentation.view.BetListRecyclerviewAdapter;

import java.util.ArrayList;

public interface BetListContract {
    interface View extends BaseView {
        void onBettingSuccess();

        void onTotalAmountCalculated(double totalAmount);

        void onBalanceLoaded(String balance);

        void onPointLoaded(String point);
    }

    interface Presenter extends BasePresenter<View> {
        void onBetWithBalance();
        void onBetWithPoint();

        /**to get the user balance*/
        void onLoadBalance();

        /**to get the user's point*/
        void loadPoint();


        /**invoked by {@link com.dingar.twok.threeD.presentation.view.BetListRecyclerviewAdapter#onBindViewHolder(BetListRecyclerviewAdapter.ViewHolder, int)}
         * whenever bet amount is changed or lottery is removed
         **/
        void setBetList(ArrayList<LotteryModel> lotteryModels);

        /**
         *to calculate initial total amount. all amount are same for all lotteries
         */
        void calculateTotalAmount();

        /**
         * invoked by {@link BetListRecyclerviewAdapter} when a lottery number removed
         */
        void onBetRemoved();

        /**
         * invoked by {@link BetListRecyclerviewAdapter} when the amount of the lottery number is changed
         * @param amount the new amount of the lottery number
         */
        void onAmountChanged(int position,String amount);


    }
}
