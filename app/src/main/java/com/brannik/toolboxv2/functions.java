package com.brannik.toolboxv2;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class functions  {
    Context applicationContext = MainActivity.getContextOfApplication();
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext);
    public String switchValue(String value){
        // switch states
        String returnData;
        if(value.equals("ON")){
            returnData="OFF";
        }else{
            returnData="ON";
        }
        return returnData;
    }

    public String getStatus(String element){
        String status;
        status = prefs.getString(element,null);
        return status;
    }

    public void makeChange(String element,String state){
        SharedPreferences.Editor editor = prefs.edit();
        switch(element){
            case "drlState":
                editor.putString("drlState", state);
                editor.apply();
                editor.commit();
                break;
            case "interState":
                editor.putString("interState", state);
                editor.apply();
                editor.commit();
                break;
            case "ampState":
                editor.putString("ampState", state);
                editor.apply();
                editor.commit();
                break;
            case "dvrState":
                editor.putString("dvrState", state);
                editor.apply();
                editor.commit();
                break;
            default:
                break;
        }
    }

    public Boolean sendRequestWaitForRespond(String element,String state){
        // send new data over wifi and wait for back call
        if(true){
            makeChange(element,switchValue(state));
            return true;
        }else{
            return false;
        }
    }

    // void findWifi(){}
    // void connectWifi(){}
    // void sendData(){}

    public void sendDefaults(){
        // send all default settings over wifi
    }
}

/*
* drlState = prefs.getString("drlState",null);
            interState = prefs.getString("interState",null);
            ampState = prefs.getString("ampState",null);
            dvrState = prefs.getString("dvrState",null);
            defDrlState = prefs.getString("defDrlState",null);
            defInterState = prefs.getString("defInterState",null);
            defAmpState = prefs.getString("defAmpState",null);
            defDvrState = prefs.getString("defDvrState",null);
            drlDelay = prefs.getString("drlDelay",null);
            interDelay = prefs.getString("interDelay",null);
            ampDelay = prefs.getString("ampDelay",null);
            dvrDelay = prefs.getString("dvrDelay",null);
            daytimeStart = prefs.getString("daytimeStart",null);
            daytimeEnd = prefs.getString("daytimeEnd",null);
* */