package com.brannik.toolboxv2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.widget.Toast;

public class trackCharging extends BroadcastReceiver  {
    Context cont = MainActivity.getContextOfApplication();
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
                break;
            default:
                strState = "not charging";
        }
        Toast.makeText(context, "CHANGED !!! " + strState, Toast.LENGTH_SHORT).show();
    }

}