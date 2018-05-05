package com.example.dongja94.sampleanimation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView, animationView;

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

        imageView = (ImageView)findViewById(R.id.imageView);
        Button btn = (Button)findViewById(R.id.btn_start);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation();
            }
        });

        btn = (Button)findViewById(R.id.btn_stop);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAnimation();
            }
        });

        animationView = (ImageView)findViewById(R.id.imageView2);
        btn = (Button)findViewById(R.id.btn_move);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.translate);
                animationView.startAnimation(anim);
            }
        });

        btn = (Button)findViewById(R.id.btn_rotate);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate);
                animationView.startAnimation(anim);
            }
        });

        btn = (Button)findViewById(R.id.btn_scale);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale);
                animationView.startAnimation(anim);
            }
        });

        btn = (Button)findViewById(R.id.btn_alpha);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.alpha);
                animationView.startAnimation(anim);
            }
        });

        btn = (Button)findViewById(R.id.btn_set1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.set1);
                animationView.startAnimation(anim);
            }
        });

        btn = (Button)findViewById(R.id.btn_set2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.set2);
                animationView.startAnimation(anim);
            }
        });

        btn = (Button)findViewById(R.id.btn_out);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.left_out);
                anim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        animationView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                animationView.startAnimation(anim);
            }
        });

        btn = (Button)findViewById(R.id.btn_in);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationView.setVisibility(View.VISIBLE);
                Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.left_in);
                animationView.startAnimation(anim);
            }
        });

        btn = (Button)findViewById(R.id.btn_custom);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.left_in);
                MyAnimation anim = new MyAnimation();
                anim.setDuration(1000);
                animationView.startAnimation(anim);
            }
        });

        btn = (Button)findViewById(R.id.btn_3d);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.left_in);
                My3DAnimation anim = new My3DAnimation();
                anim.setDuration(1000);
                animationView.startAnimation(anim);
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus) {
//            startAnimation();
//        } else {
//            stopAnimation();
//        }
    }

    private void startAnimation() {
        AnimationDrawable d = (AnimationDrawable)imageView.getDrawable();
        d.start();
    }

    private void stopAnimation() {
        AnimationDrawable d = (AnimationDrawable)imageView.getDrawable();
        d.stop();
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
