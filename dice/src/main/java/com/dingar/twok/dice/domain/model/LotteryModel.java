package com.dingar.twok.dice.domain.model;

import android.os.Parcelable;

import java.io.Serializable;

public class LotteryModel  implements Serializable {
    private String lotteryNumber;
    private boolean selected;
    private int amount;

    public LotteryModel(String lotteryNumber, boolean selected) {
        this.lotteryNumber = lotteryNumber;
        this.selected = selected;
        this.amount = 0;
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
