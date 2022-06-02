package com.dingar.twok.twoK.data.model;

import java.io.Serializable;

/**
 * data model with user selected lucky number and amount
 */
public class LotteryModel  implements Serializable {
    private String lotteryNumber;
    /**
     * {@link com.dingar.twok.twoK.presentation.view.Activity_DiceBet}
     * to know if the user selected the lucky number or not
     * help in loading recyclerview on scroll not to randomly selected
     */
    private boolean selected;
    private int amount;

    public LotteryModel(String lotteryNumber, boolean selected) {
        this.lotteryNumber = lotteryNumber;
        this.selected = selected;
        this.amount = 0;
    }

    public LotteryModel(String lotteryNumber, int amount) {
        this.lotteryNumber = lotteryNumber;
        this.amount = amount;
    }

    public String getLotteryNumber() {
        return lotteryNumber;
    }

    public void setLotteryNumber(String lotteryNumber) {
        this.lotteryNumber = lotteryNumber;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
