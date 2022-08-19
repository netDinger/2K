package com.dingar.twok.dice.data.remoteDataSource;

import com.dingar.twok.core.firebase.Result;

import io.reactivex.Single;

public class FirebaseCheckBetable {
    //singleton
    private FirebaseCheckBetable(){}
    private static FirebaseCheckBetable instance;
    public static FirebaseCheckBetable getInstance(){
        if (instance == null)
            instance = new FirebaseCheckBetable();
        return instance;
    }

    //TODO implement the code to check if the user can
    // still bet to the specific win lottery or not
    public Single<Result> checkBetable(String timeStamp){
        return null;
    }
}
