package com.brannik.toolboxv2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.widget.Toast;

public class trackCharging extends BroadcastReceiver  {
    Context cont = MainActivity.getContextOfApplication();
    WifiManager wifi = (WifiManager) cont.getSystemService(cont.WIFI_SERVICE);
    @Override
    public void onReceive(Context context, Intent intent) {
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = cont.registerReceiver(null, filter);

        int chargeState = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        String strState;
        switch (chargeState) {
            case BatteryManager.BATTERY_STATUS_CHARGING:
            case BatteryManager.BATTERY_STATUS_FULL:
                strState = "charging";
                wifi.setWifiEnabled(true);
                // connect to wifi
                break;
            default:
                strState = "not charging";
                wifi.setWifiEnabled(false);
        }
        //Toast.makeText(context, "CHANGED !!! " + strState, Toast.LENGTH_SHORT).show();
    }

}