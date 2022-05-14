package com.dingar.twok.dice.data.remoteDataSource;

import io.reactivex.Observable;

public class FirebaseGetWinDates {
    //Singleton
    private FirebaseGetWinDates(){}
    private static FirebaseGetWinDates instance;
    public static FirebaseGetWinDates getInstance(){
        if (instance == null)
            instance = new FirebaseGetWinDates();
        return instance;
    }

    /**
     * @return available lottery win announcing time for a week
     */
    public Observable<String> winDates(){
        return Observable.create(emitter -> {

        });
    }
}
