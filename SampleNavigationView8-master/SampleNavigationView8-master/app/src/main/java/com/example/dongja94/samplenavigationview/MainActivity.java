package com.example.dongja94.samplenavigationview;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    NavigationView naviView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = (TextView)findViewById(R.id.text_header);
        tv.setText("Header Setting...");
        naviView = (NavigationView)findViewById(R.id.navi);
        naviView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case R.id.menu_item1 :
            case R.id.menu_item2 :
            case R.id.menu_item3 :
                menuItem.setChecked(true);
                Toast.makeText(this, "Check Item Select : " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_sub1 :
            case R.id.menu_sub2 :
                Toast.makeText(this, "Sub Item Click : " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }
}
