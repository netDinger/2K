package com.dingar.twok.threeD.data.remoteDataSource;

import com.dingar.twok.core.firebase.Result;

import io.reactivex.Single;

public class FirebaseCheckBetable {
    //Singleton
    private FirebaseCheckBetable(){}
    private static FirebaseCheckBetable instance;
    public static FirebaseCheckBetable getInstance(){
        if (instance == null)
            instance = new FirebaseCheckBetable();
        return instance;
    }

    public Single<Result> checkBetable(String date){
        return null;
    }
}
