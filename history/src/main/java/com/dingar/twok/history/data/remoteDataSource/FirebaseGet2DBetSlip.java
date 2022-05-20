package com.dingar.twok.history.data.remoteDataSource;

import com.dingar.twok.history.data.model.BetSlipModel;

import io.reactivex.Observable;

public class FirebaseGet2DBetSlip {
    //Singleton
    private FirebaseGet2DBetSlip(){}
    private static FirebaseGet2DBetSlip instance;
    public static FirebaseGet2DBetSlip getInstance(){
        if (instance == null)
            instance = new FirebaseGet2DBetSlip();
        return instance;
    }

    public Observable<BetSlipModel> get2DBetSlips(){
        return Observable.create(emitter -> {

        });
    }
}
