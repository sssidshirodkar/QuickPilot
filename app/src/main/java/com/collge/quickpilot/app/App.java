package com.collge.quickpilot.app;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * Created by ashish on 2/4/17.
 */

import com.collge.quickpilot.util.foregroundDetector.ForegroundDetector;

public class App extends Application {

    public static App context;

    public static Context getInstance(){
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        ForegroundDetector.init(this);
        ForegroundDetector.get().addListener(new ForegroundDetector.Listener() {
            @Override
            public void onBecameForeground() {
                Log.v("quick ride", "in foreground");
            }

            @Override
            public void onBecameBackground() {
                Log.v("quick ride", "in background");
            }
        });
    }

}
