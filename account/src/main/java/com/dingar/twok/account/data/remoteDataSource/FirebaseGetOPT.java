package com.dingar.twok.account.data.remoteDataSource;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

public class FirebaseGetOPT {
    //Singleton
    private FirebaseGetOPT(){}
    private static FirebaseGetOPT instance;
    public static FirebaseGetOPT getInstance(){
        if (instance == null)
            instance = new FirebaseGetOPT();
        return instance;
    }

    public Single<String> getOpt(){
        return Single.create(emitter -> {
            //Todo implement later
        });
    }
}
