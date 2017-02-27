package com.collge.quickpilot.firebase_database;

import com.collge.quickpilot.pojo.Ride;
import com.collge.quickpilot.ui.interfaces.Callback_v2;
import com.collge.quickpilot.util.Constants;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;


/**
 * Created by Siddhesh on 25-01-2017.
 */

public class RidesReference extends AbstractDatabaseReference {
    private DatabaseReference mRideRef;
    private ValueEventListener listener;
    private Callback_v2 callback;

    @Override
    public void initiateDatabase(Callback_v2 callback) {
        this.callback = callback;
        mRideRef = mDatabase.getReference(Constants.RIDE_LIST_REFERENCE);
        listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataChange(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mRideRef.addValueEventListener(listener);
    }

    public void addMyRide(Ride ride) {
        mRideRef.setValue(ride);
    }

    public void getListOfRides(final Callback_v2 callback) {
        mRideRef.orderByChild("status").equalTo(0).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                callback.invoke(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void dataChange(DataSnapshot dataSnapshot) {
        callback.invoke(dataSnapshot);
//        Log.d("Listening","dataSnapshot : "+dataSnapshot);
    }

    public void changeRideStatus(String phoneNo){
        mRideRef.child(phoneNo).setValue(1);
    }

    public DatabaseReference getRideRef(){
        return mRideRef;
    }

}
