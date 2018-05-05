package com.example.dongja94.samplefragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class OtherActivity extends AppCompatActivity implements OneFragment.OnMessageListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        OneFragment f = (OneFragment)getSupportFragmentManager().findFragmentById(R.id.fragment);
    }

    @Override
    public void receiveMessage(String message) {
        // ...
    }
}
