package com.collge.quickpilot.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.collge.quickpilot.pojo.Ride;

/**
 * Created by Siddhesh on 28-02-2017.
 */

public class MapsActivity extends AppCompatActivity {

    Ride ride;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        ride = intent.getParcelableExtra("ride");
        Log.v("ride object", ride.getMyMobile());
    }
}
