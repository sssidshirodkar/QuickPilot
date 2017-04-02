package com.collge.quickpilot.pojo;

/**
 * Created by ashish on 2/4/17.
 */

import android.content.Context;
import android.content.SharedPreferences;
import com.collge.quickpilot.app.App;
import com.collge.quickpilot.util.Constants;

public class SharedPreference {

    public static SharedPreference preference;
    public static SharedPreferences pref;

    public static SharedPreference getPreference() {
        if(preference == null){
            preference = new SharedPreference();
            pref = App.getInstance().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        }
        return preference;
    }

    public void setMyPhoneNumber(String phoneNumber){
        pref.edit().putString(Constants.MY_PHONE_NUMBER, phoneNumber).apply();
    }

    public String getMyPhoneNumber(){
        return pref.getString(Constants.MY_PHONE_NUMBER, null);
    }

}
