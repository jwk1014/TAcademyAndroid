package com.example.dongja94.samplelocation;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    LocationManager mLM;
    String mProvider;
    TextView locationView;

    public static final int MESSAGE_LOCATION_TIMEOUT = 1;
    public static final int TIMEOUT_INTERVAL = 60000;

    Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MESSAGE_LOCATION_TIMEOUT:
                    // Timeout...
                    break;
            }
        }
    };

    ListView listView;
    ArrayAdapter<Address> mAdapter;
    EditText keywordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        locationView = (TextView) findViewById(R.id.text_location);
        listView = (ListView) findViewById(R.id.listView);
        keywordView = (EditText) findViewById(R.id.edit_keyword);
        mAdapter = new ArrayAdapter<Address>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Address addr = (Address) listView.getItemAtPosition(position);

                float radius = 100;
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, 2015);
                cal.set(Calendar.MONTH, 11);
                cal.set(Calendar.DAY_OF_MONTH, 25);
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                long exired = cal.getTimeInMillis();

                Intent intent = new Intent(MainActivity.this, ProximityService.class);
                intent.putExtra("address", addr);
                intent.setData(Uri.parse("myscheme://packagename/"+id));
                PendingIntent pi = PendingIntent.getService(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }

                mLM.addProximityAlert(addr.getLatitude(), addr.getLongitude(), radius, exired, pi);
            }
        });

        Button btn = (Button)findViewById(R.id.btn_search);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = keywordView.getText().toString();
                if (!TextUtils.isEmpty(keyword)) {
                    if (Geocoder.isPresent()) {
                        Geocoder coder = new Geocoder(MainActivity.this);
                        try {
                            List<Address> list = coder.getFromLocationName(keyword,10);
                            mAdapter.clear();
                            for (Address addr : list) {
                                mAdapter.add(addr);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        mLM = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        criteria.setCostAllowed(true);
        mProvider = mLM.getBestProvider(criteria, true);
        if (mProvider == null) {
            mProvider = LocationManager.GPS_PROVIDER;
        }
    }

    LocationListener mListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            mHandler.removeMessages(MESSAGE_LOCATION_TIMEOUT);
            locationView.setText(location.getLatitude() + "," + location.getLongitude());
            if (Geocoder.isPresent()) {
                Geocoder coder = new Geocoder(MainActivity.this);
                try {
                    List<Address> list = coder.getFromLocation(location.getLatitude(), location.getLongitude(), 10);
                    mAdapter.clear();
                    for (Address addr  : list) {
                        mAdapter.add(addr);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            if (provider.equals(LocationManager.GPS_PROVIDER)) {
                switch (status) {
                    case LocationProvider.OUT_OF_SERVICE:
                    case LocationProvider.TEMPORARILY_UNAVAILABLE:
                        // Network Provider change....
                        break;
                    case LocationProvider.AVAILABLE:
                        // Gps Provider change...
                        break;
                }
            }
        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    boolean isFirst = true;

    @Override
    protected void onStart() {
        super.onStart();
        if (!mLM.isProviderEnabled(mProvider)) {
            if (isFirst) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
                isFirst = false;
            } else {
                Toast.makeText(this, "This is ....", Toast.LENGTH_SHORT).show();
                finish();
            }
            return;
        }

        if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        mLM.requestSingleUpdate(mProvider, mListener, null);
//        Location location = mLM.getLastKnownLocation(mProvider);
//        if (location != null) {
//            mListener.onLocationChanged(location);
//        }
//        mLM.requestLocationUpdates(mProvider, 0, 0, mListener);
        mHandler.sendEmptyMessageDelayed(MESSAGE_LOCATION_TIMEOUT, TIMEOUT_INTERVAL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
