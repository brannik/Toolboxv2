package com.brannik.toolboxv2;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class overlay extends Service {
    Context applicationContext = MainActivity.getContextOfApplication();
    functions action = new functions();
    String TEMP;
    @Override
    public void onCreate() {
        super.onCreate();
        status(action.getStatus("drlState"),action.getStatus("interState"),action.getStatus("ampState"),action.getStatus("dvrState"));
        // if screen is on - start worker.service else stop worker.service
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    View.OnClickListener newListener = new View.OnClickListener() {
        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ to do
        // send request over wwifi -> w8 for respond if is ok -> change state
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case 1:
                    //action.sendRequestWaitForRespond("drlState",action.getStatus("drlState"));
                    TEMP = action.switchValue(action.getStatus("drlState"));
                    action.makeChange("drlState",TEMP);
                    break;
                case 2:
                    TEMP = action.switchValue(action.getStatus("interState"));
                    action.makeChange("interState",TEMP);
                    break;
                case 3:
                    TEMP = action.switchValue(action.getStatus("ampState"));
                    action.makeChange("ampState",TEMP);
                    break;
                case 4:
                    TEMP = action.switchValue(action.getStatus("dvrState"));
                    action.makeChange("dvrState",TEMP);
                    break;
                default:
                    Log.d("DEBUG", "NEW BUTTON CLICKED > " + view.getId());
                    break;
            }
            status(action.getStatus("drlState"),action.getStatus("interState"),action.getStatus("ampState"),action.getStatus("dvrState"));
        }
    };

    @SuppressLint("ResourceType")
    public void status(String drlStatus, String interStatus, String ampStatus, String dvrStatus){
        final WindowManager.LayoutParams parameters = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                240,
                WindowManager.LayoutParams.TYPE_TOAST,   // Allows the view to be on top of the StatusBar
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,     // Keeps the button presses from going to the background window and Draws over status bar
                PixelFormat.TRANSLUCENT);
        parameters.gravity = Gravity.BOTTOM | Gravity.LEFT;
        LinearLayout ll2 = new LinearLayout(applicationContext);
        ll2.setBackgroundColor(Color.TRANSPARENT);
        LinearLayout.LayoutParams lparam = new LinearLayout.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT );
        ll2.setOrientation(LinearLayout.VERTICAL);
        ll2.setLayoutParams(lparam);
        ll2.setPadding(40,10,10,10);

        Button btn = new Button(ll2.getContext());
        if(drlStatus.equals("ON")){
            Drawable img = btn.getContext().getResources().getDrawable( R.drawable.drl_on);
            btn.setBackground(img);
        }else{
            Drawable img = btn.getContext().getResources().getDrawable( R.drawable.drl_off);
            btn.setBackground(img);
        }
        btn.setId(1);

        btn.setWidth(120);
        btn.setHeight(50);
        btn.setOnClickListener(newListener);
        ll2.addView(btn);

        Button btn2 = new Button(ll2.getContext());
        if(interStatus.equals("ON")){
            Drawable img = btn2.getContext().getResources().getDrawable( R.drawable.int_on);
            btn2.setBackground(img);
        }else{
            Drawable img = btn2.getContext().getResources().getDrawable( R.drawable.int_off);
            btn2.setBackground(img);
        }
        btn2.setId(2);

        btn2.setWidth(120);
        btn2.setHeight(50);
        btn2.setOnClickListener(newListener);
        ll2.addView(btn2);

        Button btn3 = new Button(ll2.getContext());
        if(ampStatus.equals("ON")){
            Drawable img = btn3.getContext().getResources().getDrawable( R.drawable.amp_on);
            btn3.setBackground(img);
        }else{
            Drawable img = btn3.getContext().getResources().getDrawable( R.drawable.amp_off);
            btn3.setBackground(img);
        }
        btn3.setId(3);

        btn3.setWidth(120);
        btn3.setHeight(50);
        btn3.setOnClickListener(newListener);
        ll2.addView(btn3);

        Button btn4 = new Button(ll2.getContext());
        if(dvrStatus.equals("ON")){
            Drawable img = btn4.getContext().getResources().getDrawable( R.drawable.dvr_on);
            btn4.setBackground(img);
        }else{
            Drawable img = btn4.getContext().getResources().getDrawable( R.drawable.dvr_off);
            btn4.setBackground(img);
        }
        btn4.setId(4);

        btn4.setWidth(120);
        btn4.setHeight(50);
        btn4.setOnClickListener(newListener);
        ll2.addView(btn4);


        WindowManager windowManager = (WindowManager) applicationContext.getSystemService(Context.WINDOW_SERVICE);
        windowManager.addView(ll2, parameters);
    }
}
