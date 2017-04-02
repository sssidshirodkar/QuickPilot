package com.collge.quickpilot.util.foregroundDetector;

/**
 * Created by ashish on 2/4/17.
 */

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.lang.ref.WeakReference;

public class ForegroundDetector implements Application.ActivityLifecycleCallbacks {

    public static final String TAG = ForegroundDetector.class.getName();
    public static final long CHECK_DELAY = 2000;
    Listener myListener;

//    public static long TIME_CONSTANT = 0;

    public interface Listener {
        public void onBecameForeground();

        public void onBecameBackground();
    }

    public interface Binding {
        public void unbind();
    }

    private interface Callback {
        public void invoke(Listener listener);
    }

    private static Callback becameForeground = new Callback() {
        @Override
        public void invoke(Listener listener) {
            listener.onBecameForeground();
        }
    };

    private static Callback becameBackground = new Callback() {
        @Override
        public void invoke(Listener listener) {
            listener.onBecameBackground();
        }
    };

    private static ForegroundDetector instance;

    private boolean foreground;
    private Activity current;
    private Handler handler = new Handler();
    private Runnable check;

    public static ForegroundDetector init(Application application) {
        if (instance == null) {
            instance = new ForegroundDetector();
            application.registerActivityLifecycleCallbacks(instance);
        }
        return instance;
    }

    public static ForegroundDetector get(Application application) {
        if (instance == null) {
            init(application);
        }
        return instance;
    }

    public static ForegroundDetector get() {
        if (instance == null) {
            throw new IllegalStateException(
                    "Foreground is not initialised - first invocation must use parameterised init/get");
        }
        return instance;
    }

    public boolean isForeground() {
        return foreground;
    }

    public boolean isBackground() {
        return !foreground;
    }

    public void addListener(Listener listener) {
        myListener = listener;
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
        // if we're changing configurations we aren't going background so
        // no need to schedule the check
        if (!activity.isChangingConfigurations()) {
            // don't prevent activity being gc'd
            final WeakReference<Activity> ref = new WeakReference<>(activity);
            handler.postDelayed(check = new Runnable() {
                @Override
                public void run() {
                    onActivityCeased(ref.get());
                }
            }, CHECK_DELAY);
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {
        current = activity;
        // remove any scheduled checks since we're starting another activity
        // we're definitely not going background
        if (check != null) {
            handler.removeCallbacks(check);
        }

        // check if we're becoming foreground and notify listeners
        if (!foreground && (activity != null && !activity.isChangingConfigurations())) {
            foreground = true;
            Log.w(TAG, "became foreground");
            becameForeground.invoke(myListener);
        } else {
            Log.i(TAG, "still foreground");
        }
    }

    @Override
    public void onActivityStopped(Activity activity) {
        if (check != null) {
            handler.removeCallbacks(check);
        }
        onActivityCeased(activity);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
    }


    private void onActivityCeased(Activity activity) {
        if (foreground) {
            if ((activity == current) && (activity != null && !activity.isChangingConfigurations())) {
                foreground = false;
                Log.w(TAG, "went background");
                becameBackground.invoke(myListener);
            } else {
                Log.i(TAG, "still foreground");
            }
        } else {
            Log.i(TAG, "still background");
        }
    }

}