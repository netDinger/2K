package com.dingar.twok.phae.data.remoteDataSource;

import androidx.annotation.NonNull;

import com.dingar.twok.firebaseadapter.Static_Config;

import com.dingar.twok.phae.data.model.WinLotteryModel;
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
                    .child(Static_Config.PHAE)
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                                emitter.onNext(new WinLotteryModel(dataSnapshot.getKey(),
                                        String.valueOf(dataSnapshot.getValue())));
                            }
                            emitter.onComplete();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    })
        );
    }

}
