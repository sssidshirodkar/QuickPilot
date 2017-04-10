package com.collge.quickpilot.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.collge.quickpilot.R;
import com.collge.quickpilot.firebase_database.AbstractDatabaseReference;
import com.collge.quickpilot.firebase_database.RidesReference;
import com.collge.quickpilot.util.Constants;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.collge.quickpilot.app.App.context;

/**
 * Created by Siddhesh on 21-03-2017.
 */

public class RideProgressActivity extends AppCompatActivity {

    String sourceLat, sourceLng, destLat, destLng;
    Button end_ride, navigate;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activit_ride_progress);

        Intent intent = getIntent();
        sourceLat = intent.getStringExtra("sourceLat");
        sourceLng = intent.getStringExtra("sourceLng");
        destLat = intent.getStringExtra("destLat");
        destLng = intent.getStringExtra("destLng");
        String u = Constants.RIDE_REFERENCE +"/" +intent.getStringExtra("myMobile") + "/status";
        Log.d("------URL----->",u);
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        mDatabase.getReference()
                .child("quickride")
                .child("rides")
                .child(intent.getStringExtra("myMobile"))
                .child("mRikMobile")
                .setValue(LoginActivity.mRikMobile);
        mDatabase.getReference()
                .child("quickride")
                .child("rides")
                .child(intent.getStringExtra("myMobile"))
                .child("status")
                .setValue(1);


        openNavigator(sourceLat, sourceLng, destLat, destLng);

        end_ride = (Button) findViewById(R.id.end_ride);
        end_ride.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        navigate = (Button) findViewById(R.id.navigate);
        navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNavigator(sourceLat, sourceLng, destLat, destLng);
            }
        });


    }

    /**
     * Updates the address in the UI.
     * @param sourceLat
     * @param sourceLng
     * @param destLat
     * @param destLng
     */
    public void openNavigator(String sourceLat, String sourceLng, String destLat, String destLng) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?saddr=" + sourceLat + "," + sourceLng + "&daddr=" + destLat + "," + destLng));
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }

}
