package com.dingar.twok.twoK.data.remoteDataSource;

import com.dingar.twok.core.firebase.Result;

import com.dingar.twok.firebaseadapter.Static_Config;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
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
        return Single.create(emitter -> {
            FirebaseDatabase.getInstance().getReference().child(Static_Config.BETABLE_DATE)
                .child(Static_Config.TWOK)
                .child(date)
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
