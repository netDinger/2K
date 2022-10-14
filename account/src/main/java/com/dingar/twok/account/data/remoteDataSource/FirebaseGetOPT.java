package com.dingar.twok.account.data.remoteDataSource;

import androidx.annotation.NonNull;

import com.dingar.twok.firebaseadapter.Get_Current_User;
import com.dingar.twok.firebaseadapter.Static_Config;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.reactivex.Single;

/**
 * when user withdraw the balance, user have to get the OTP code in order to identify he is the owner
 */
public class FirebaseGetOPT {
    //Singleton
    private FirebaseGetOPT(){}
    private static FirebaseGetOPT instance;
    public static FirebaseGetOPT getInstance(){
        if (instance == null)
            instance = new FirebaseGetOPT();
        return instance;
    }

    public Single<String> getOpt(){
        return Single.create(emitter -> FirebaseDatabase.getInstance().getReference()
                .child(Static_Config.OTP)
              .child(Get_Current_User.getCurrentUserID())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try {
                            if (snapshot.exists())
                                emitter.onSuccess(snapshot.getValue(String.class));
                            else emitter.onError(new Exception("No Code To Get!"));
                        }catch (Exception exception){
                            emitter.onError(exception);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        emitter.onError(new Exception("Error Retrieving Code!!!"));
                    }
                }));
    }
}
