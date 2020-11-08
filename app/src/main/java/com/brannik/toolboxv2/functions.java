package com.brannik.toolboxv2;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class functions extends AppCompatActivity {
    Context cont = MainActivity.getContextOfApplication();
    SharedPreferences prefs = MainActivity.prefs;
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


    public void sendDefaults(){

        if(compareDates()){
            // send defDrlState to original state
            String data ="http://192.168.4.1/defaults?"
                    .concat("drl=" + MainActivity.defDrlState.toLowerCase())
                    .concat("&int=" + MainActivity.defInterState.toLowerCase())
                    .concat("&amp=" + MainActivity.defAmpState.toLowerCase())
                    .concat("&dvr=" + MainActivity.defDvrState.toLowerCase())
                    .concat("&drlDelay=" + MainActivity.drlDelay.toLowerCase())
                    .concat("&intDelay=" + MainActivity.interDelay.toLowerCase())
                    .concat("&ampDelay=" + MainActivity.ampDelay.toLowerCase())
                    .concat("&dvrDelay" + MainActivity.dvrDelay.toLowerCase());
            new GetMethodDemo().execute(data);
        }else{
            String data ="http://192.168.4.1/defaults?"
                    .concat("drl=off")
                    .concat("&int=on")
                    .concat("&amp=" + MainActivity.defAmpState.toLowerCase())
                    .concat("&dvr=" + MainActivity.defDvrState.toLowerCase())
                    .concat("&drlDelay=" + MainActivity.drlDelay.toLowerCase())
                    .concat("&intDelay=" + MainActivity.interDelay.toLowerCase())
                    .concat("&ampDelay=" + MainActivity.ampDelay.toLowerCase())
                    .concat("&dvrDelay" + MainActivity.dvrDelay.toLowerCase());
            new GetMethodDemo().execute(data);
        }
        //new GetMethodDemo().execute("http://192.168.4.1/defaults?drl=on&int=on&amp=on&dvr=on&drlDelay=10&intDelay=10&ampDelay=10&dvrDelay=10");

    }

    private Date date;
    private Date dateCompareOne;
    private Date dateCompareTwo;

    private String compareStringOne ;
    private String compareStringTwo ;


    SimpleDateFormat inputParser = new SimpleDateFormat("HH:mm",Locale.ROOT);

    public Boolean compareDates(){
        compareStringOne = String.format("%s:00",MainActivity.daytimeStart) ;
        compareStringTwo = String.format("%s:00",MainActivity.daytimeEnd) ;
        //Log.d("DEBUG",compareStringOne);
        //Log.d("DEBUG",compareStringTwo);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String s = sdf.format(new Date());

        date = parseDate(s);
        dateCompareOne = parseDate(compareStringOne);
        dateCompareTwo = parseDate(compareStringTwo);
        //Log.d("DEBUG",date.toString());
        //Log.d("DEBUG",dateCompareOne.toString());
        //Log.d("DEBUG",dateCompareTwo.toString());
        if(date.after(dateCompareOne) && date.before(dateCompareTwo)){
            //Log.d("DEBUG","Is between");
            return true;
        }else{
            //Log.d("DEBUG","Is not between");
            return false;
        }
    }

    private Date parseDate(String date) {

        try {
            return inputParser.parse(date);
        } catch (ParseException e) {
            return new Date(0);
        }
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