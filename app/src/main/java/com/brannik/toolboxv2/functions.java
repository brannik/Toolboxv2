package com.brannik.toolboxv2;


public class functions  {

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