package com.dingar.twok.core.firebase;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import io.reactivex.Single;

public class Get_FirebaseCurrentTime {
    //Singleton
    private Get_FirebaseCurrentTime(){}
    private static Get_FirebaseCurrentTime instance;
    public static Get_FirebaseCurrentTime getInstance(){
        if (instance == null)
            instance = new Get_FirebaseCurrentTime();
        return instance;
    }


    //Get the current time of firebase server
    public Single<String> gerCurrentTime() throws Exception{
        return Single.create(emitter -> FirebaseDatabase.getInstance().getReference()
        .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                        emitter.onSuccess(Objects.requireNonNull(String.valueOf(snapshot.getValue())));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                throw error.toException();
            }
        }));
    }
}
