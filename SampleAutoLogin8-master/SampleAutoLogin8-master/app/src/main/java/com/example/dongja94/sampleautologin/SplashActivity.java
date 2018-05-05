package com.example.dongja94.sampleautologin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
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

        String id = PropertyManager.getInstance().getId();
        if (!TextUtils.isEmpty(id)) {
            String password = PropertyManager.getInstance().getPassword();
            NetworkManager.getInstance().login(id, password, new NetworkManager.OnResultListener<String>() {
                @Override
                public void onSuccess(String result) {
                    if (result.equals("ok")) {
                        goMain();
                    } else {
                        goLogin();
                    }
                }

                @Override
                public void onFail(int code) {
                    goLogin();
                }
            });
        } else {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    goLogin();
                }
            }, 1000);
        }
    }

    Handler mHandler = new Handler(Looper.getMainLooper());

    private void goMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void goLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

}
