package com.brannik.toolboxv2;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class functions {
    Context applicationContext = MainActivity.getContextOfApplication();
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext);
    String drlState,interState,ampState,dvrState,defDrlState,defInterState,defAmpState,defDvrState,drlDelay,interDelay,ampDelay,dvrDelay,daytimeStart,daytimeEnd;
    
    public void getSettings(){
        // read shared prefs
        if (prefs.contains("drlState") & prefs.contains("daytimeEnd")) {
            drlState = prefs.getString("drlState",null);

        } else {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("drlState", "OFF");
            editor.putString("interState", "OFF");
            editor.putString("ampState", "OFF");
            editor.putString("dvrState", "OFF");

            editor.putString("defDrlState", "OFF");
            editor.putString("defInterState", "OFF");
            editor.putString("defAmpState", "OFF");
            editor.putString("defDvrState", "OFF");

            editor.putString("drlDelay", "10");
            editor.putString("interDelay", "10");
            editor.putString("ampDelay", "10");
            editor.putString("dvrDelay", "10");

            editor.putString("daytimeStart", "6");
            editor.putString("daytimeEnd", "18");
            editor.apply();
            editor.commit();
        }
    }
    public void setSettings(){
        // write shared prefs
    }
    public void changeValue(){
        // switch states
    }
}
