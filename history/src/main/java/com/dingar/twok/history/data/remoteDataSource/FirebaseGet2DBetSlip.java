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
import com.google.firebase.database.ValueEventListener;

import io.reactivex.Observable;

/**
 * to get all user bet slips for 2D
 */
public class FirebaseGet2DBetSlip {
    private final String TAG = "FirebaseGet2DBetSlip";

    //Singleton
    private FirebaseGet2DBetSlip(){}
    private static FirebaseGet2DBetSlip instance;
    public static FirebaseGet2DBetSlip getInstance(){
        if (instance == null)
            instance = new FirebaseGet2DBetSlip();
        return instance;
    }

    public Observable<BetSlipModel> get2DBetSlips(){
        return Observable.create(emitter -> {
            FirebaseDatabase.getInstance().getReference()
                    .child(Static_Config.BETSLIP)// BetSlip
                    .child(Static_Config.TWOD)//TwoD
                    .orderByChild(Static_Config.UID)
                    .equalTo(Get_Current_User.getCurrentUserID())
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()){
                                try {
                                    BetSlipModel betSlipModel = snapshot.getValue(BetSlipModel.class);
                                    assert betSlipModel != null;
                                    betSlipModel.setBetSlipId(snapshot.getKey());
                                    emitter.onNext(betSlipModel);
                                }catch (Exception e){
                                    Log.e(TAG,e.getMessage());
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        });
    }
}
