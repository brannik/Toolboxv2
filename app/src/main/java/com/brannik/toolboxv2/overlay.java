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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class overlay extends Service {
    Context applicationContext = MainActivity.getContextOfApplication();
    functions action = new functions();
    public overlay() {
    }
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
        @Override
        public void onClick(View view) {
            Log.d("DEBUG", "NEW BUTTON CLICKED > " + view.getId());
            if(view.getId() == 1) {

            }
        }
    };

    public void status(String drlStatus,String interStatus,String ampStatus,String dvrStatus){
        final WindowManager.LayoutParams parameters = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                240,
                WindowManager.LayoutParams.TYPE_TOAST,   // Allows the view to be on top of the StatusBar
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,     // Keeps the button presses from going to the background window and Draws over status bar
                PixelFormat.TRANSLUCENT);
        parameters.gravity = Gravity.BOTTOM | Gravity.LEFT;


        LinearLayout ll2 = new LinearLayout(applicationContext);
        ll2.setBackgroundColor(Color.TRANSPARENT);
        LinearLayout.LayoutParams lparam = new LinearLayout.LayoutParams(390,210 );

        ll2.setOrientation(LinearLayout.HORIZONTAL);
        ll2.setLayoutParams(lparam);

        Button btn3 = new Button(applicationContext);
        if(drlStatus.equals("ON")){
            @SuppressLint("UseCompatLoadingForDrawables") Drawable img = btn3.getContext().getResources().getDrawable( R.drawable.drl_on);
            btn3.setBackground(img);
        }else{
            Drawable img = btn3.getContext().getResources().getDrawable( R.drawable.drl_off);
            btn3.setBackground(img);
        }
        btn3.setId(3);
        btn3.setGravity(Gravity.LEFT | Gravity.TOP);
        btn3.setOnClickListener(newListener);
        ll2.addView(btn3);

        Button btn4 = new Button(applicationContext);
        btn4.setX(5);
        if(interStatus.equals("ON")){
            Drawable img = btn4.getContext().getResources().getDrawable( R.drawable.int_on);
            btn4.setBackground(img);
        }else{
            Drawable img = btn4.getContext().getResources().getDrawable( R.drawable.int_off);
            btn4.setBackground(img);
        }
        btn4.setId(4);

        btn4.setGravity(Gravity.RIGHT | Gravity.TOP);
        btn4.setOnClickListener(newListener);
        ll2.addView(btn4);

        // center layout and images
        LinearLayout ll3 = new LinearLayout(applicationContext);
        ll3.setBackgroundColor(Color.TRANSPARENT);
        LinearLayout.LayoutParams layoutParameteres2 = new LinearLayout.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        ll3.setOrientation(LinearLayout.HORIZONTAL);
        ll3.setGravity(Gravity.CENTER);
        ll3.setLayoutParams(layoutParameteres2);

        ImageView img3 = new ImageView(ll3.getContext());
        Drawable img2 = img3.getContext().getResources().getDrawable( R.drawable.s_neutral);
        img3.setImageDrawable(img2);
        img3.setPadding(81,8,8,96);
        ll3.addView(img3);


        LinearLayout ll = new LinearLayout(applicationContext);
        ll.setBackgroundColor(Color.TRANSPARENT);
        LinearLayout.LayoutParams layoutParameteres = new LinearLayout.LayoutParams(390,210);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setY(80);
        ll.setLayoutParams(layoutParameteres);

        Button btn = new Button(applicationContext);

        if(ampStatus.equals("ON")){
            Drawable img = btn.getContext().getResources().getDrawable( R.drawable.amp_on);
            btn.setBackground(img);
        }else{
            Drawable img = btn.getContext().getResources().getDrawable( R.drawable.amp_off);
            btn.setBackground(img);
        }

        btn.setId(1);
        btn.setGravity(Gravity.LEFT | Gravity.BOTTOM);
        btn.setOnClickListener(newListener);
        ll.addView(btn);


        Button btn2 = new Button(applicationContext);
        btn2.setX(5);
        if(dvrStatus.equals("ON")){
            Drawable img = btn2.getContext().getResources().getDrawable( R.drawable.dvr_on);
            btn2.setBackground(img);
        }else{
            Drawable img = btn2.getContext().getResources().getDrawable( R.drawable.dvr_off);
            btn2.setBackground(img);
        }

        //btn.setText("DRL " + drlStatus);
        btn2.setId(2);

        btn2.setGravity(Gravity.RIGHT | Gravity.BOTTOM);
        btn2.setOnClickListener(newListener);
        ll.addView(btn2);

        WindowManager windowManager = (WindowManager) applicationContext.getSystemService(Context.WINDOW_SERVICE);

        windowManager.addView(ll3, parameters);
        windowManager.addView(ll2, parameters);
        windowManager.addView(ll, parameters);

    }
}
