package com.example.dongja94.sampleapplicationcomponent;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private static final String TAG = "MyService";
    public static final String ACTION_MOD_TEN = "com.example.dongja94.sampleapplicationcomponent.action.MOD_TEN";
    boolean isRunning = false;
    int mCount = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "onCreate...", Toast.LENGTH_SHORT).show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                isRunning = true;
                while(isRunning) {
                    Log.i(TAG, "count : " + mCount);
                    if (mCount % 10 == 0) {
                        Intent intent = new Intent(ACTION_MOD_TEN);
                        intent.putExtra("count", mCount);
//                        sendBroadcast(intent);
                        sendOrderedBroadcast(intent, null, new BroadcastReceiver() {
                            @Override
                            public void onReceive(Context context, Intent intent) {
                                int code = getResultCode();
                                if (code == Activity.RESULT_CANCELED) {
                                    Toast.makeText(MyService.this, "Activity Not Processed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, null, Activity.RESULT_CANCELED , null, null);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mCount++;
                }
            }
        }).start();

        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);

        registerReceiver(mScreenReceiver, filter);
    }

    BroadcastReceiver mScreenReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                Toast.makeText(context, "Screen On", Toast.LENGTH_SHORT).show();
            } else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                Log.i(TAG, "Screen off");
            }
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "onStartCommand...", Toast.LENGTH_SHORT).show();
        if (intent != null) {
            mCount = intent.getIntExtra("count", 0);
        }
        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "onDestroy...", Toast.LENGTH_SHORT).show();
        super.onDestroy();
        isRunning = false;
        unregisterReceiver(mScreenReceiver);
    }
}
