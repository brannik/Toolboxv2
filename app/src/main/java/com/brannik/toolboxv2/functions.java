package com.brannik.toolboxv2;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class functions extends AppCompatActivity {
    Context cont = MainActivity.getContextOfApplication();

    // wifi data



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


    public void sendData(){
        new GetMethodDemo().execute("your web-service url");
    }

    public void sendDefaults(){

        // send all default settings over wifi
        // if time is between dayTimeStart and dayTimeEnd then defDrlState = defDrlState else -> defDrlState = OFF aways
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