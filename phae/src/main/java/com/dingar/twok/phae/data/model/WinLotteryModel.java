package com.dingar.twok.phae.data.model;

public class WinLotteryModel {

    String date;
    String lucky_number;
    boolean win;

    public WinLotteryModel(){}

    public WinLotteryModel(String date, String lucky_number) {
        this.date = date;
        this.lucky_number = lucky_number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLucky_number() {
        return lucky_number;
    }

    public void setLucky_number(String lucky_number) {
        this.lucky_number = lucky_number;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }
}
