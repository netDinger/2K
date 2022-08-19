package com.dingar.twok.dice.presentation.contract;

import com.dingar.twok.core.BasePresenter;
import com.dingar.twok.dice.domain.model.LotteryModel;

import java.util.ArrayList;

public interface DiceBetContract {

    interface View{

        /**
         * on available lottery list is loaded
         * @param lotteries list of available (bet able) lottery list
         */
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



        /**
         * @param timeRemaining remaining timer countdown in string format
         * {@link Presenter#loadTimeRemaining()}
         */
        void setTimeRemaining(String timeRemaining);

        /**
         *
         * @param date bet able win date
         * {@link Presenter#loadBetableTime()}
         */
        void onBetAbleTimeLoaded(ArrayList<String> date);

        /**
         * show the warning or error dialog
         * @param title title of the alertdialog
         * @param message message of the alertdialog
         * @param cancelable is the dialog cancelable or not
         */
        void showDialog(String title,String message,boolean cancelable);

        /**
         * send betSlip if user meet the validation(amount,betDate...)
         * {@link Presenter#checkBetable(String)}
         */
        void bet();
    }

    interface Presenter extends BasePresenter<View> {
        /**
         * load the available lotteries by excluding the unavailable lotteries
         */
        void loadLotteries();

        /**
         *
         * @param amount bet amount
         * @return if amount is empty or less than minimum amount
         */
        boolean isStringValid(String amount);

        /**
         *load the future time when the next lottery will be opened
         * example: 1:21:21 to open next lucky number
         */
        void loadTimeRemaining();

        /**
         * load the available bet able times (lottery opening time) as admin set
         * example: win dates { 11.11.2022 4:00PM,12.11.2022 10:00AM }
         */
        void loadBetableTime();

        /**
         * check if the user can still bet for the specific time or not
         * @param date win date which user want to bet
         */
        void checkBetable(String date);
    }

}
