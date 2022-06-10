package com.dingar.twok.firebaseadapter;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import io.reactivex.Single;

/**
 * get user's point
 */
public class GetPoint {
    //Singleton
    private GetPoint(){}
    private static GetPoint instance;
    public static GetPoint getInstance(){
        if (instance == null)
            instance = new GetPoint();
        return instance;
    }

    public Single<Double> getPoints(){
        return Single.create(emitter -> {
            FirebaseDatabase.getInstance().getReference(Static_Config.BALANCE)
            .child(Get_Current_User.getCurrentUserID())
            .child(Static_Config.POINT)
            .addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        try {
                            emitter.onSuccess(Double.parseDouble(Objects.requireNonNull(snapshot.getValue(String.class))));
                        }
                        catch(Exception exception){
                            emitter.onError(exception);
                        }
                    }
                    else
                        emitter.onError(new NullPointerException("No data"));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    emitter.onError(error.toException());
                }
            });
        });
    }
}
