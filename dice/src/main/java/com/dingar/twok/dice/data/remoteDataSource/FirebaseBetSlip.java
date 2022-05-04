package com.dingar.twok.dice.data.remoteDataSource;

import android.util.Log;

import com.dingar.twok.core.firebase.Result;
import com.dingar.twok.dice.domain.model.LotteryModel;
import com.dingar.twok.firebaseadapter.Get_Current_User;
import com.dingar.twok.firebaseadapter.Static_Config;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.Single;

public class FirebaseBetSlip {
    //Singleton
    private static FirebaseBetSlip instance;
    private int count = 0;
    private FirebaseBetSlip(){}
    public static FirebaseBetSlip getInstance(){
        if (instance == null)
            instance = new FirebaseBetSlip();
        return instance;
    }

    public Single<Result> bet(ArrayList<LotteryModel> lotteryModels){
        return Single.create(emitter -> {
            count = 0;
            for (LotteryModel lotteryModel :lotteryModels){
                FirebaseDatabase.getInstance().getReference().child(Static_Config.BETSLIP)
                        .child(Get_Current_User.getCurrentUserID())
                        .child(Static_Config.TWOD)
                        .child(lotteryModel.getLotteryNumber())
                        .child(String.valueOf(System.currentTimeMillis()))
                        .setValue(lotteryModel.getAmount())
                        .addOnSuccessListener(unused -> {
                            count+=1;
                            if (count == lotteryModels.size())
                                emitter.onSuccess(new Result(true));
                        })
                .addOnFailureListener(e -> {
                    count +=1;
                    if (count == lotteryModels.size())
                        emitter.onSuccess(new Result(true));
                    Log.e("bet error",e.getMessage());
                });


            }

        });
    }
}
