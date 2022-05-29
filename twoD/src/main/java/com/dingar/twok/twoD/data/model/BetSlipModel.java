package com.dingar.twok.twoD.data.model;

import java.io.Serializable;

/**
 * BetSlip model for retrieving user's bet list
 */
public class BetSlipModel implements Serializable {

    private String lottery;
    private int amount;
    private String winDateTimeStamp;

    public BetSlipModel(String lottery, int amount) {
        this.lottery = lottery;
        this.amount = amount;
    }

    public String getLottery() {
        return lottery;
    }

    public void setLottery(String lottery) {
        this.lottery = lottery;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
