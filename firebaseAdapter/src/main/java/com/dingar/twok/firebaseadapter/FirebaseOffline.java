package com.dingar.twok.firebaseadapter;

import com.google.firebase.database.FirebaseDatabase;

public class FirebaseOffline {
    public static void offlineUse(){
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
