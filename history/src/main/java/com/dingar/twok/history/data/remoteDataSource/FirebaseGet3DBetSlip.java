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
 * get all user bet slip for 3d
 */
public class FirebaseGet3DBetSlip {
    private final String TAG ="FirebaseGet3DBetSlip";

    //Singleton
    private FirebaseGet3DBetSlip(){}
    private static FirebaseGet3DBetSlip instance;
    public static FirebaseGet3DBetSlip getInstance(){
        if (instance == null)
            instance = new FirebaseGet3DBetSlip();
        return instance;

    }

    public Observable<BetSlipModel> get3DBetSlips(){
        return Observable.create(emitter -> {
            FirebaseDatabase.getInstance().getReference()
                    .child(Static_Config.BETSLIP)// BetSlip
                    .child(Static_Config.THREED)//threeD
                    .child(Get_Current_User.getCurrentUserID())//$uid
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
                                    Log.e(TAG,e.getMessage());
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
