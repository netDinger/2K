package com.dingar.twok.dice.data.remoteDataSource;

import android.util.Log;

import com.dingar.twok.core.firebase.Result;
import com.dingar.twok.dice.domain.model.LotteryModel;
import com.dingar.twok.firebaseadapter.Get_Current_User;
import com.dingar.twok.firebaseadapter.Static_Config;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.ArrayList;
import java.util.HashMap;

import io.grpc.Server;
import io.reactivex.Single;


/**
 * send the user selected betSlips to server
 */
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
                HashMap<String,Object> betMap = new HashMap<>();
                betMap.put(Static_Config.AMOUNT,String.valueOf(lotteryModel.getAmount()));
                betMap.put(Static_Config.WINDATE,"not yet");
                betMap.put(Static_Config.BETDATE,ServerValue.TIMESTAMP);
                betMap.put(Static_Config.LUCKYNO,lotteryModel.getLotteryNumber());

                FirebaseDatabase.getInstance().getReference()
                        .child(Static_Config.BETSLIP)// BetSlip
                        .child(Static_Config.TWOD)//TwoD
                        .child(Get_Current_User.getCurrentUserID()) //$uid
                        .push()
                        .updateChildren(betMap)
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
