package com.dingar.twok.twoK.data.remoteDataSource;

import androidx.annotation.NonNull;

import com.dingar.twok.firebaseadapter.Static_Config;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import io.reactivex.Observable;

/**
 * load the excluded bets by admin. To create available bet list
 */
public class FirebaseLoadBets {

    //Singleton building
    private static FirebaseLoadBets instance;
    private FirebaseLoadBets(){}
    public static FirebaseLoadBets getInstance(){
        if (instance == null)
            instance = new FirebaseLoadBets();
        return instance;
    }

    public Observable<String> loadBets(){
        return Observable.create(emitter -> {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
                    .child(Static_Config.EXCLUDED_BET).child(Static_Config.TWOK);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            emitter.onNext(Objects.requireNonNull(dataSnapshot.getKey()));
                        }
                    }
                    emitter.onComplete();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    emitter.onComplete();
                }
            });


        });
    }


}
