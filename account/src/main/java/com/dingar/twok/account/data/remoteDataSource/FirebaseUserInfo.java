package com.dingar.twok.account.data.remoteDataSource;

import androidx.annotation.NonNull;

import com.dingar.twok.account.data.model.User;
import com.dingar.twok.firebaseadapter.Get_Current_User;
import com.dingar.twok.firebaseadapter.Static_Config;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.reactivex.Single;

public class FirebaseUserInfo {
    //Singleton
    private FirebaseUserInfo(){}
    private static FirebaseUserInfo instance;
    public static FirebaseUserInfo getInstance(){
        if (instance == null)
            instance = new FirebaseUserInfo();
        return instance;
    }

    public Single<User> getUserInfo(){
        return Single.create(emitter ->
            FirebaseDatabase.getInstance().getReference(Static_Config.USERS)
                    .child(Get_Current_User.getCurrentUserID())
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            try {
                                User user = snapshot.getValue(User.class);
                                user.setUid(snapshot.getKey());

                                emitter.onSuccess(user);
                            }catch (Exception e){
                                emitter.onError(e);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            emitter.onError(error.toException());
                        }
                    })
        );
    }
}