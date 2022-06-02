package com.dingar.twok.phae.data.remoteDataSource;

import androidx.annotation.NonNull;

import com.dingar.twok.firebaseadapter.Static_Config;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.reactivex.Observable;

/**
 * get the bet able times (lottery opening time) as admin set
 * {@link com.dingar.twok.phae.domain.repository.BetableTimeRepository}
 */
public class FirebaseGetBetableTimes {

    //Singleton
    private FirebaseGetBetableTimes(){}
    private static FirebaseGetBetableTimes instance;
    public static FirebaseGetBetableTimes getInstance(){
        if (instance == null)
            instance = new FirebaseGetBetableTimes();
        return instance;
    }

    public Observable<String> getTime(){

        return Observable.create(emitter ->
            FirebaseDatabase.getInstance().getReference(Static_Config.BETABLE_DATE)
                    .child(Static_Config.PHAE)
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //get all date and tell the observer
                            for (DataSnapshot dataSnapshot : snapshot.getChildren())
                                emitter.onNext(String.valueOf(dataSnapshot.getKey()));

                            emitter.onComplete();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            emitter.onError(error.toException());
                        }
                    })
        );
    }
}
