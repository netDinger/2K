package com.dingar.twok.dice.data.model;

import java.io.Serializable;

public class BetSlipModel implements Serializable {

    private String lottery;
    private int amount;

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
