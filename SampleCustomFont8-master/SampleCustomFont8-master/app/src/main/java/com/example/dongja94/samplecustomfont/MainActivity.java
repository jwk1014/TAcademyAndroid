package com.example.dongja94.samplecustomfont;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView)findViewById(R.id.text_message);
        tv.setTypeface(FontManager.getInstance().getTypeface(this, FontManager.NANUM));
    }
}
