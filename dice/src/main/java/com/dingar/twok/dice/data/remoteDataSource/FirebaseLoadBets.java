package com.dingar.twok.dice.data.remoteDataSource;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import io.reactivex.Observable;

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
        Log.e("loading","bets");
        return Observable.create(emitter -> {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
                    .child("excludedbet").child("twod");

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
