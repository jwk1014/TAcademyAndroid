package com.example.dongja94.samplefragmentviewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by dongja94 on 2015-10-08.
 */
public class MyAdapter extends FragmentStatePagerAdapter {
    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return TitleFragment.newInstance("title : " + position);
    }

    @Override
    public float getPageWidth(int position) {
        return 0.93f;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
