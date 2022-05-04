package com.dingar.twok.dice.data.remoteDataSource;


import androidx.annotation.NonNull;

import com.dingar.twok.firebaseadapter.Static_Config;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

public class FirebaseGetTimeRemaining {
    //Singleton
    private FirebaseGetTimeRemaining(){}
    private static FirebaseGetTimeRemaining instance;
    public static FirebaseGetTimeRemaining getInstance(){
        if (instance == null)
            instance = new FirebaseGetTimeRemaining();
        return instance;
    }

    public Single<String> getTimeRemaining(){
       return Single.create(emitter -> FirebaseDatabase.getInstance().getReference()
               .child(Static_Config.NEXT_WIN)
               .child(Static_Config.TWOD)
               .addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                       emitter.onSuccess(Objects.requireNonNull(snapshot.getValue(String.class)));
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {
                       emitter.onError(error.toException());
                   }
               }));
    }
}
