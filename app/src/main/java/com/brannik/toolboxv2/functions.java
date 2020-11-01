package com.brannik.toolboxv2;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class functions  {
    Context applicationContext = MainActivity.getContextOfApplication();
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext);

    public void setSettings(){
        // write shared prefs
    }
    public void changeValue(String element){
        // switch states
        switch(element){

        }
    }
}
