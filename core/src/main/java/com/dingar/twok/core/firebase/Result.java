package com.dingar.twok.core.firebase;

public class Result {
    public boolean Success;

    public Result(){}
    public Result(boolean success) {
        Success = success;
    }


    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }


}
