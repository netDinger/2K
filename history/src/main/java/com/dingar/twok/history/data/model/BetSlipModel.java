package com.dingar.twok.history.data.model;

import com.google.firebase.database.PropertyName;

/**
 * to show the user bets
 */
public class BetSlipModel implements Comparable<BetSlipModel>{
    @PropertyName("betDate")
    long date;

    @PropertyName("luckyNo")
    String luckyNumber;

    @PropertyName("amount")
    String amount;

    @PropertyName("winDate")
    long winDate;

    String betSlipId;

    @PropertyName("isWin")
    boolean isWin;

    boolean visible;

    public BetSlipModel(){}

    public BetSlipModel(long date, String luckyNumber, String amount, String betSlipId, boolean isWin) {
        this.date = date;
        this.luckyNumber = luckyNumber;
        this.amount = amount;
        this.betSlipId = betSlipId;
        this.isWin = isWin;
        this.visible = false;
    }


    @PropertyName("betDate")
    public long getDate() {
        return date;
    }

    @PropertyName("betDate")
    public void setDate(long date) {
        this.date = date;
    }

    @PropertyName("luckyNo")
    public String getLuckyNumber() {
        return luckyNumber;
    }

    @PropertyName("luckyNo")
    public void setLuckyNumber(String luckyNumber) {
        this.luckyNumber = luckyNumber;
    }

    @PropertyName("amount")
    public String getAmount() {
        return amount;
    }

    @PropertyName("amount")
    public void setAmount(String amount) {
        this.amount = amount;
    }

    public long getWinDate() {
        return winDate;
    }

    public void setWinDate(long winDate) {
        this.winDate = winDate;
    }

    public String getBetSlipId() {
        return betSlipId;
    }

    public void setBetSlipId(String betSlipId) {
        this.betSlipId = betSlipId;
    }

    public boolean isWin() {
        return isWin;
    }

    public void setWin(boolean win) {
        isWin = win;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }


    @Override public int compareTo(BetSlipModel betSlipModel) {
        return this.getDate()> betSlipModel.getDate()?1:-1;
    }
}
