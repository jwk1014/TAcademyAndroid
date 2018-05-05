package com.example.dongja94.samplefragmenttab;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    FragmentTabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabHost = (FragmentTabHost)findViewById(R.id.tabHost);
        tabHost.setup(this,getSupportFragmentManager(), R.id.realtabcontent);

        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("TAB1"), Tab1Fragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("TAB2"), Tab2Fragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("TAB3"), Tab3Fragment.class, null);


    }
}
