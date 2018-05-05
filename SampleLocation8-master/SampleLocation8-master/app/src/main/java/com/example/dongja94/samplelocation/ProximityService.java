package com.example.dongja94.samplelocation;

import android.app.Service;
import android.content.Intent;
import android.location.Address;
import android.location.LocationManager;
import android.os.IBinder;
import android.widget.Toast;

public class ProximityService extends Service {
    public ProximityService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        boolean isEnter = intent.getBooleanExtra(LocationManager.KEY_PROXIMITY_ENTERING, false);
        Address addr = intent.getParcelableExtra("address");
        if (isEnter) {
            Toast.makeText(this, "enter : ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "exit : " , Toast.LENGTH_SHORT).show();
        }
        return Service.START_NOT_STICKY;
    }
}
