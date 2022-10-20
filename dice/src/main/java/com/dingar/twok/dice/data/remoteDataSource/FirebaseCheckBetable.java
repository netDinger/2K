package com.dingar.twok.dice.data.remoteDataSource;

import com.dingar.twok.core.firebase.Result;

import com.dingar.twok.firebaseadapter.Static_Config;
import com.google.firebase.database.FirebaseDatabase;
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
        return Single.create(emitter -> {
            FirebaseDatabase.getInstance().getReference().child(Static_Config.BETABLE_DATE)
                .child(Static_Config.DICE)
                .child(timeStamp)
                .get().addOnSuccessListener(dataSnapshot -> {
                    if (dataSnapshot.exists())
                        emitter.onSuccess(new Result(
                            Boolean.TRUE.equals(dataSnapshot.getValue(Boolean.class))));

                    else emitter.onError(new Exception("ထိုးကြေးတင်လို့မရပါ..."));
                })
                .addOnFailureListener(emitter::onError);
        });
    }

}
