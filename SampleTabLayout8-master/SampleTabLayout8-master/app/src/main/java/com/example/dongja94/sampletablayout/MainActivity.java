package com.example.dongja94.sampletablayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TabLayout tabs;
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabs = (TabLayout) findViewById(R.id.tabs);

        pager = (ViewPager)findViewById(R.id.pager);
        final MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        tabs.setupWithViewPager(pager);

//        tabs.removeAllTabs();
//        for (int i = 0; i < adapter.getCount(); i++) {
//            tabs.addTab(tabs.newTab().setText(adapter.getPageTitle(i)));
//
//            tabs.addTab(tabs.newTab().setIcon( ));
//
//            tabs.addTab(tabs.newTab().setCustomView( ));
//        }

//        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.container, adapter.getItem(tab.getPosition()));
//                fragmentTransaction.commit();
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {}
//        });

//        tabs.setTabsFromPagerAdapter(adpater);

//        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.container, BlankFragment.newInstance(tab.getText().toString()))
//                        .commit();
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
    }
}
