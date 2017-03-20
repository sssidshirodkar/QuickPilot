package com.collge.quickpilot.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.collge.quickpilot.R;
import com.collge.quickpilot.firebase_database.GeoFireReference;
import com.collge.quickpilot.firebase_database.RidesReference;
import com.collge.quickpilot.firebase_database.UserReference;
import com.collge.quickpilot.pojo.Ride;
import com.collge.quickpilot.ui.adapter.RideListAdapter;
import com.collge.quickpilot.ui.interfaces.Callback_v2;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class RideListActivity extends AppCompatActivity {

    ArrayList<Ride> rides;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    UserReference userReference;
    RidesReference ridesReference;
    GeoFireReference geoFireReference;
    RideListAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rides = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.rlView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerAdapter = new RideListAdapter(this, rides);
        recyclerView.setAdapter(recyclerAdapter);

        initiateDatabase();

    }

    private void initiateDatabase() {
        userReference = new UserReference();
        userReference.initiateDatabase(new Callback_v2() {
            @Override
            public void invoke(Object o) {
                Log.d("Listening", "in maps activity : " + (DataSnapshot) o);
                //Toast.makeText(RideListActivity.this, "dataSnapshot" + (DataSnapshot) o, Toast.LENGTH_SHORT).show();
            }
        });

        ridesReference = new RidesReference();
        ridesReference.initiateDatabase(new Callback_v2() {
            @Override
            public void invoke(Object o) {
//                Log.d("Listening", "in maps activity : " + (DataSnapshot) o);
//                Toast.makeText(RideListActivity.this, "dataSnapshot" + (DataSnapshot) o, Toast.LENGTH_SHORT).show();
//
//                rides.clear();
//                for (DataSnapshot dataSnapshot : ((DataSnapshot) o).getChildren()) {
//                    Ride ride = dataSnapshot.getValue(Ride.class);
//                    rides.add(ride);
//                }
//                recyclerAdapter.notifyDataSetChanged();
            }
        });

        ridesReference.getListOfRides(new Callback_v2() {
            @Override
            public void invoke(Object o) {
                //Toast.makeText(RideListActivity.this, "dataSnapshot" + (DataSnapshot) o, Toast.LENGTH_SHORT).show();

                rides.clear();
                for (DataSnapshot dataSnapshot : ((DataSnapshot) o).getChildren()) {
                    Ride ride = dataSnapshot.getValue(Ride.class);
                    rides.add(ride);
                }
                recyclerAdapter.notifyDataSetChanged();
            }
        });

        geoFireReference = new GeoFireReference();
        geoFireReference.initiateDatabase(new Callback_v2() {
            @Override
            public void invoke(Object o) {

            }
        });
    }

    public void changeRideStatus(String phoneNo){
        ridesReference.getRideRef().child(phoneNo).child("status").setValue(1);
    }

}
