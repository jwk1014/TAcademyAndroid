package com.example.dongja94.samplefragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OneFragment.OnMessageListener {

    private static final String F1_TAG = "f1";
    private static final String F2_TAG = "f2";

    Fragment f1,f2, current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button)findViewById(R.id.btn_f1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment old = getSupportFragmentManager().findFragmentByTag(F1_TAG);
                if (old == null) {
//                    Fragment f = new OneFragment();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    if (current != null) {
                        ft.detach(current);
                    }
                    ft.add(R.id.container, f1, F1_TAG);
                    ft.commit();
                    current = f1;
                } else if (old != current) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.detach(current);
                    ft.attach(f1);
                    ft.commit();
                    current = f1;
                }
            }
        });

        btn = (Button)findViewById(R.id.btn_f2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment old = getSupportFragmentManager().findFragmentByTag(F2_TAG);
                if (old == null) {
//                    Fragment f = new TwoFragment();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    if (current != null) {
                        ft.detach(current);
                    }
                    ft.add(R.id.container, f2, F2_TAG);
                    ft.commit();
                    current = f2;
                } else if (current != f2) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.detach(current);
                    ft.attach(f2);
                    ft.commit();
                    current = f2;
                }
            }
        });

        f1 = OneFragment.instantiate("Hi Fragment");
        ((OneFragment)f1).setOnMessageListener(new OneFragment.OnMessageListener() {
            @Override
            public void receiveMessage(String message) {
                // ...
            }
        });
        f2 = new TwoFragment();
        btn = (Button)findViewById(R.id.btn_other);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OtherActivity.class));
            }
        });

//        Fragment f = new OneFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container, f1, F1_TAG);
        ft.commit();
        current = f1;

    }


    public void receiveMessage(String message) {
        Toast.makeText(this, "message : " + message, Toast.LENGTH_SHORT).show();
    }
}
