package com.dingar.twok.twoK.data.remoteDataSource;

import android.util.Log;
import androidx.annotation.NonNull;


import com.dingar.twok.firebaseadapter.Static_Config;

import com.dingar.twok.twoK.data.model.WinLotteryModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.reactivex.Observable;

/**get the previous win history for 1 months
 *
 */
public class FirebaseGetWinHistory {
    //singleton
    private FirebaseGetWinHistory(){}
    private static FirebaseGetWinHistory instance;
    public static FirebaseGetWinHistory getInstance(){
        if (instance == null)
            instance = new FirebaseGetWinHistory();
        return instance;
    }

    public Observable<WinLotteryModel> getWinHistory(){
        return Observable.create(emitter ->
            FirebaseDatabase.getInstance().getReference().child(Static_Config.LUCKY_NUMBERS)
                    .child(Static_Config.TWOK)
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                          if (snapshot.exists()){
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                              Log.e("datasnapshot",String.valueOf(dataSnapshot.getValue()));
                                emitter.onNext(new WinLotteryModel(dataSnapshot.getKey(),
                                        String.valueOf(dataSnapshot.getValue())));
                            }
                            emitter.onComplete();
                          }
                          else emitter.onError(new Exception("No data"));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    })
        );
    }

}
