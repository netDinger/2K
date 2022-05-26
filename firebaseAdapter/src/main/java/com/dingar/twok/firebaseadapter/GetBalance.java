package com.dingar.twok.firebaseadapter;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import io.reactivex.Single;

public class GetBalance {
    private static GetBalance instance;
    private GetBalance(){}
    public static GetBalance getInstance(){
        if (instance == null)
            instance = new GetBalance();
        return instance;
    }

    public Single<Double> gtBalance(){
        return Single.create(emitter -> {
            FirebaseDatabase.getInstance().getReference().child(Static_Config.USERS)
                    .child(Get_Current_User.getCurrentUserID())
                    .child(Static_Config.BALANCE)
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()){
                            try {
                                emitter.onSuccess((Double) Objects.requireNonNull(snapshot.getValue()));
                            }
                            catch(Exception exception){
                                exception.printStackTrace();
                            }
                            }
                            else
                               emitter.onError(new NullPointerException("LOL no data"));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            emitter.onError(error.toException());
                        }
                    });
        });
    }
}
