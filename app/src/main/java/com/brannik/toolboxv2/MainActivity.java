package com.brannik.toolboxv2;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.BatteryManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static Context contextOfApplication;
    public static Context getContextOfApplication(){
        return contextOfApplication;
    }
    String drlState,interState,ampState,dvrState;
    public static String defDrlState,defInterState,defAmpState,defDvrState,drlDelay,interDelay,ampDelay,dvrDelay;
    public static String daytimeStart,daytimeEnd;
    public static SharedPreferences prefs;
    functions action = new functions();
    Boolean frun = true;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contextOfApplication = getApplicationContext();
        prefs  = PreferenceManager.getDefaultSharedPreferences(contextOfApplication);
        getData();
        if(frun){
            action.sendDefaults();
            frun = false;
            StartService();
            if(action.compareDates()){
                drlState = defDrlState;
                interState = defInterState;
                ampState = defAmpState;
                dvrState = defDvrState;
            }else{
                drlState = "OFF";
                interState = "ON";
                ampState = defAmpState;
                dvrState = defDvrState;
            }

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("drlState", drlState);
            editor.putString("interState", interState);
            editor.putString("ampState", ampState);
            editor.putString("dvrState", dvrState);
            editor.apply();
            editor.commit();
        }


        //trackBattery();

        TextView txtDrlDelay = findViewById(R.id.txtDrlDelayValue);
        TextView txtInterDelay = findViewById(R.id.txtInterDelayValue);
        TextView txtAmpDelay = findViewById(R.id.txtAmpDelayValue);
        TextView txtDvrDelay = findViewById(R.id.txtDvrDelayValue);
        ToggleButton tglDrlDefState = findViewById(R.id.tglDrl);
        ToggleButton tglInterDefState = findViewById(R.id.tglInterior);
        ToggleButton tglAmpDefState = findViewById(R.id.tglAmp);
        ToggleButton tglDvrDefState = findViewById(R.id.tglDVR);
        Button btnSave = findViewById(R.id.btnSave);

        TextView txtDaytimeStart = findViewById(R.id.txtDaytimeStartValue);
        TextView txtDayTimeEnd = findViewById(R.id.txtDaytimeEndValue);

        tglDrlDefState.setOnClickListener(this);
        tglInterDefState.setOnClickListener(this);
        tglAmpDefState.setOnClickListener(this);
        tglDvrDefState.setOnClickListener(this);
        btnSave.setOnClickListener(this);

        txtDrlDelay.setText(drlDelay);
        txtInterDelay.setText(interDelay);
        txtAmpDelay.setText(ampDelay);
        txtDvrDelay.setText(dvrDelay);

        txtDaytimeStart.setText(daytimeStart);
        txtDayTimeEnd.setText(daytimeEnd);

        if(defDrlState.equals("ON")){
            tglDrlDefState.setChecked(true);
        }else{
            tglDrlDefState.setChecked(false);
        }

        if(defInterState.equals("ON")){
            tglInterDefState.setChecked(true);
        }else{
            tglInterDefState.setChecked(false);
        }

        if(defAmpState.equals("ON")){
            tglAmpDefState.setChecked(true);
        }else{
            tglAmpDefState.setChecked(false);
        }

        if(defDvrState.equals("ON")){
            tglDvrDefState.setChecked(true);
        }else{
            tglDvrDefState.setChecked(false);
        }

    }

    @Override
    public void onClick(View view) {
        String temp;
        switch (view.getId()){
            case R.id.tglDrl:
                Log.d("DEBUG","DRL");
                temp = action.switchValue(defDrlState);
                defDrlState = temp;
                break;
            case R.id.tglInterior:
                Log.d("DEBUG","INTERIOR");
                temp = action.switchValue(defInterState);
                defInterState = temp;
                break;
            case R.id.tglAmp:
                Log.d("DEBUG","AMP");
                temp = action.switchValue(defAmpState);
                defAmpState = temp;
                break;
            case R.id.tglDVR:
                Log.d("DEBUG","DVR");
                temp = action.switchValue(defDvrState);
                defDvrState = temp;
                break;
            case R.id.btnSave:
                Log.d("DEBUG","SAVE");
                saveData();
                break;
            default:
                Log.d("DEBUG","No Button");
        }
    }

    public void saveData(){
        SharedPreferences.Editor editor = prefs.edit();

        TextView txtDrlDelay = findViewById(R.id.txtDrlDelayValue);
        drlDelay = txtDrlDelay.getText().toString();

        TextView txtInterDelay = findViewById(R.id.txtInterDelayValue);
        interDelay = txtInterDelay.getText().toString();

        TextView txtAmpDelay = findViewById(R.id.txtAmpDelayValue);
        ampDelay = txtAmpDelay.getText().toString();

        TextView txtDvrDelay = findViewById(R.id.txtDrlDelayValue);
        dvrDelay = txtDvrDelay.getText().toString();

        TextView txtDaytimeStart = findViewById(R.id.txtDaytimeStartValue);
        daytimeStart = txtDaytimeStart.getText().toString();

        TextView txtDaytimeEnd = findViewById(R.id.txtDaytimeEndValue);
        daytimeEnd = txtDaytimeEnd.getText().toString();

        editor.putString("drlState", drlState);
        editor.putString("interState", interState);
        editor.putString("ampState", ampState);
        editor.putString("dvrState", dvrState);

        editor.putString("defDrlState", defDrlState);
        editor.putString("defInterState", defInterState);
        editor.putString("defAmpState", defAmpState);
        editor.putString("defDvrState", defDvrState);

        editor.putString("drlDelay", drlDelay);
        editor.putString("interDelay", interDelay);
        editor.putString("ampDelay", ampDelay);
        editor.putString("dvrDelay", dvrDelay);

        editor.putString("daytimeStart", daytimeStart);
        editor.putString("daytimeEnd", daytimeEnd);
        editor.apply();
        editor.commit();
        action.sendDefaults();
    }


    public void getData(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(contextOfApplication);
        if (prefs.contains("drlState") & prefs.contains("daytimeEnd")) {
            drlState = prefs.getString("drlState",null);
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

    public void StartService(){
        startService(new Intent(this, worker.class));
        startService(new Intent(this, overlay.class));
    }

    public void stopService(){
        stopService(new Intent(getBaseContext(), worker.class));
        stopService(new Intent(getBaseContext(), overlay.class));
    }
}