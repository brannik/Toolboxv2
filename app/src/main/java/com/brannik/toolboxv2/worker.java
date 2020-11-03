package com.brannik.toolboxv2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
// perform wifi connection and transcieve data
public class worker extends Service {
    public worker() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
