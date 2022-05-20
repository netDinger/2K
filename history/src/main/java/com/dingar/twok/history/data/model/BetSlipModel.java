package com.dingar.twok.history.data.model;

public class BetSlipModel {
    String date;
    String luckyNumber;
    String amount;
    String betSlipId;
    boolean isWin;

    public BetSlipModel(String date, String luckyNumber, String amount, String betSlipId, boolean isWin) {
        this.date = date;
        this.luckyNumber = luckyNumber;
        this.amount = amount;
        this.betSlipId = betSlipId;
        this.isWin = isWin;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLuckyNumber() {
        return luckyNumber;
    }

    public void setLuckyNumber(String luckyNumber) {
        this.luckyNumber = luckyNumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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
}
