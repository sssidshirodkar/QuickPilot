package com.collge.quickpilot.firebase_database;

import com.collge.quickpilot.ui.interfaces.Callback_v2;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;


/**
 * Created by Siddhesh on 25-01-2017.
 */

public abstract class AbstractDatabaseReference {
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();

    abstract public void initiateDatabase(Callback_v2 callback);
    abstract public void dataChange(DataSnapshot dataSnapshot);

    private static String myMobile;

    public static String getMyMobile() {
        return myMobile;
    }

    public static void setMyMobile(String myMobile) {
        AbstractDatabaseReference.myMobile = myMobile;
    }
}
