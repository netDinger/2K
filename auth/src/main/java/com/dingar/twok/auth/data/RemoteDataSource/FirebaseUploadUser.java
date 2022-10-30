package com.dingar.twok.auth.data.RemoteDataSource;

import android.util.Log;

import com.dingar.twok.core.firebase.Result;
import com.dingar.twok.firebaseadapter.Get_Current_User;
import com.dingar.twok.firebaseadapter.Static_Config;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Upload new user information
 */
public class FirebaseUploadUser {

    String TAG = "FirebaseUploadUser";

    @Inject
    public FirebaseUploadUser(){}

    public Single<Result> uploadUser(String username,String phoneNumber){

        return Single.create(emitter -> {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference(Static_Config.USERS);
            Map<String,Object> userMap = new HashMap<>();
            userMap.put("name", username);
            userMap.put("phone",phoneNumber);
            userMap.put("verified",false);
            reference.child(Get_Current_User.getCurrentUserID()).updateChildren(userMap)
                    .addOnCompleteListener(task -> {
                        //if upload user info complete
                        Log.e(TAG,"upload user complete");
                        if (task.isSuccessful())
                            emitter.onSuccess(new Result(true));
                        else
                            emitter.onSuccess(new Result(false));
            });
        });
    }
}

