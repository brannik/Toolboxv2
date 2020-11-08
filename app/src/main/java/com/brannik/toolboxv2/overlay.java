package com.brannik.toolboxv2;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.UnsupportedEncodingException;

public class overlay extends Service {
    Context applicationContext = MainActivity.getContextOfApplication();
    functions action = new functions();
    public static Boolean check=true;

    @Override
    public void onCreate() {
        super.onCreate();
        status(action.getStatus("drlState"), action.getStatus("interState"), action.getStatus("ampState"), action.getStatus("dvrState"));

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
            String state;
            switch(view.getId()){

                case 1:
                    state = action.getStatus("drlState");
                    new GetMethodDemo().execute("http://192.168.4.1/switch?element=drl&val=" + state.toLowerCase());

                    if(check){
                        action.makeChange("drlState",action.switchValue(action.getStatus("drlState")));
                    }else{
                        Toast.makeText(applicationContext,"Error: " + check.toString(),Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 2:
                    state = action.getStatus("interState");
                    new GetMethodDemo().execute("http://192.168.4.1/switch?element=int&val=" + state.toLowerCase());

                    if(check){
                        action.makeChange("interState",action.switchValue(action.getStatus("interState")));
                    }else{
                        Toast.makeText(applicationContext,"Error: " + check.toString(),Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 3:
                    state = action.getStatus("ampState");
                    new GetMethodDemo().execute("http://192.168.4.1/switch?element=amp&val=" + state.toLowerCase());

                    if(check){
                        action.makeChange("ampState",action.switchValue(action.getStatus("ampState")));
                    }else{
                        Toast.makeText(applicationContext,"Error: " + check.toString(),Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 4:
                    state = action.getStatus("dvrState");
                    new GetMethodDemo().execute("http://192.168.4.1/switch?element=dvr&val=" + state.toLowerCase());

                    if(check){
                        action.makeChange("dvrState",action.switchValue(action.getStatus("dvrState")));
                    }else{
                        Toast.makeText(applicationContext,"Error: " + check.toString(),Toast.LENGTH_SHORT).show();
                    }
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
