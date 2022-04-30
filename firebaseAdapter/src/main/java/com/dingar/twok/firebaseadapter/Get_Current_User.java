package com.dingar.twok.firebaseadapter;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Get_Current_User {
    @NonNull
    public static String getCurrentUserID(){
        return Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
    }
}
