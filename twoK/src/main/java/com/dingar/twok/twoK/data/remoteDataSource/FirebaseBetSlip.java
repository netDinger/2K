package com.dingar.twok.twoK.data.remoteDataSource;

import android.util.Log;

import com.dingar.twok.core.firebase.Result;
import com.dingar.twok.firebaseadapter.Get_Current_User;
import com.dingar.twok.firebaseadapter.Static_Config;
import com.dingar.twok.twoK.data.model.LotteryModel;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.Single;


/**
 * send the user selected betSlips to server
 */
public class FirebaseBetSlip {
    //Singleton
    private static FirebaseBetSlip instance;
    private FirebaseBetSlip(){}
    public static FirebaseBetSlip getInstance(){
        if (instance == null)
            instance = new FirebaseBetSlip();
        return instance;
    }

    private int count = 0;  // for counting array
    //to store the successfully betted bet slip amount and calculate the new balance
    private int totalAmount;

    public Single<Result> bet(ArrayList<LotteryModel> lotteryModels,double balance){
        return Single.create(emitter -> {
            count = 0;
            totalAmount = 0;
            for (LotteryModel lotteryModel :lotteryModels){
                try {
                    HashMap<String, Object> betMap = new HashMap<>();
                    betMap.put(Static_Config.AMOUNT, String.valueOf(lotteryModel.getAmount()));
                    betMap.put(Static_Config.WINDATE, lotteryModel.getWinDate());
                    betMap.put(Static_Config.BETDATE, ServerValue.TIMESTAMP);
                    betMap.put(Static_Config.LUCKYNO, lotteryModel.getLotteryNumber());

                    FirebaseDatabase.getInstance().getReference()
                            .child(Static_Config.BETSLIP)// BetSlip
                            .child(Static_Config.TWOK)//TwoK
                            .child(Get_Current_User.getCurrentUserID()) //$uid
                            .push()
                            .updateChildren(betMap)
                            .addOnSuccessListener(unused -> {
                                count += 1;
                                //add amount to totalAmount on successfully betted
                                totalAmount += lotteryModel.getAmount();
                                if (count == lotteryModels.size()) { //means all lotteries are uploaded
                                    //calculate the new balance by subtracting totalAmount from balance
                                    double newBalance = balance - totalAmount;
                                    //and upload new balance to firebase (only on client side)
                                    FirebaseDatabase.getInstance().getReference().child(Static_Config.BALANCE)
                                            .child(Get_Current_User.getCurrentUserID())
                                            .child(Static_Config.BALANCE)
                                            .setValue(newBalance)
                                            .addOnCompleteListener(task -> emitter.onSuccess(new Result(true)));

                                }

                            })
                            .addOnFailureListener(e -> {
                                count += 1;
                                if (count == lotteryModels.size()) { //means all lotteries are uploaded
                                    //calculate the new balance by subtracting totalAmount from balance
                                    double newBalance = balance - totalAmount;
                                    //and upload new balance to firebase (only on client side)
                                    FirebaseDatabase.getInstance().getReference().child(Static_Config.BALANCE)
                                            .child(Get_Current_User.getCurrentUserID())
                                            .child(Static_Config.BALANCE)
                                            .setValue(newBalance)
                                            .addOnCompleteListener(task -> emitter.onSuccess(new Result(true)));
                                }

                                Log.e("bet error", e.getMessage());
                            });
                }catch (Exception e){
                    Log.e("bet error",e.getMessage());
                }
            }

        });
    }


    public Single<Result> betByPoint(ArrayList<LotteryModel> lotteryModels,double points){
        return Single.create(emitter -> {
            count = 0;
            totalAmount = 0;
            for (LotteryModel lotteryModel :lotteryModels){
                try {
                    HashMap<String, Object> betMap = new HashMap<>();
                    betMap.put(Static_Config.AMOUNT, String.valueOf(lotteryModel.getAmount()));
                    betMap.put(Static_Config.WINDATE, lotteryModel.getWinDate());
                    betMap.put(Static_Config.BETDATE, ServerValue.TIMESTAMP);
                    betMap.put(Static_Config.LUCKYNO, lotteryModel.getLotteryNumber());

                    FirebaseDatabase.getInstance().getReference()
                            .child(Static_Config.BETSLIP)// BetSlip
                            .child(Static_Config.TWOK)//TwoK
                            .child(Get_Current_User.getCurrentUserID()) //$uid
                            .push()
                            .updateChildren(betMap)
                            .addOnSuccessListener(unused -> {
                                count += 1;
                                //add amount to totalAmount on successfully betted
                                totalAmount += lotteryModel.getAmount();
                                if (count == lotteryModels.size()) { //means all lotteries are uploaded
                                    //calculate the new balance by subtracting totalAmount from balance
                                    double newBalance = points - totalAmount;
                                    //and upload new balance to firebase (only on client side)
                                    FirebaseDatabase.getInstance().getReference().child(Static_Config.BALANCE)
                                            .child(Get_Current_User.getCurrentUserID())
                                            .child(Static_Config.POINT)
                                            .setValue(newBalance)
                                            .addOnCompleteListener(task -> emitter.onSuccess(new Result(true)));
                                }

                            })
                            .addOnFailureListener(e -> {
                                count += 1;
                                if (count == lotteryModels.size()) { //means all lotteries are uploaded
                                    //calculate the new balance by subtracting totalAmount from balance
                                    double newBalance = points - totalAmount;
                                    //and upload new balance to firebase (only on client side)
                                    FirebaseDatabase.getInstance().getReference().child(Static_Config.BALANCE)
                                            .child(Get_Current_User.getCurrentUserID())
                                            .child(Static_Config.POINT)
                                            .setValue(newBalance)
                                            .addOnCompleteListener(task -> emitter.onSuccess(new Result(true)));
                                }

                                Log.e("bet error", e.getMessage());
                            });
                }catch (Exception e){
                    Log.e("bet error",e.getMessage());
                }
            }

        });
    }

}
