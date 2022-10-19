package com.dingar.twok.history.data.remoteDataSource;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dingar.twok.firebaseadapter.Get_Current_User;
import com.dingar.twok.firebaseadapter.Static_Config;
import com.dingar.twok.history.data.model.BetSlipModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import io.reactivex.Observable;

/**
 * Get user bet slip for dice bet
 */
public class FirebaseGetDiceBetSlip {
    private final String TAG ="FirebaseGetDiceBetSlip";

    //Singleton
    private FirebaseGetDiceBetSlip(){}
    private static FirebaseGetDiceBetSlip instance;
    public static FirebaseGetDiceBetSlip getInstance(){
        if (instance == null)
            instance = new FirebaseGetDiceBetSlip();
        return instance;

    }

    public Observable<BetSlipModel> getDiceBetSlips(){
        return Observable.create(emitter -> {
            FirebaseDatabase.getInstance().getReference()
                    .child(Static_Config.BETSLIP)// BetSlip
                    .child(Static_Config.DICE)//dice
                    .orderByChild(Static_Config.UID)
                    .equalTo(Get_Current_User.getCurrentUserID())
                    .addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                            if (snapshot.exists()){
                                try {
                                    BetSlipModel betSlipModel = snapshot.getValue(BetSlipModel.class);
                                    assert betSlipModel != null;
                                    betSlipModel.setBetSlipId(snapshot.getKey());
                                    Log.e("is win","aaa"+ betSlipModel.isWin());
                                    emitter.onNext(betSlipModel);
                                }catch (Exception e){
                                   emitter.onError(e);
                                }
                            }
                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
        });
    }
}
