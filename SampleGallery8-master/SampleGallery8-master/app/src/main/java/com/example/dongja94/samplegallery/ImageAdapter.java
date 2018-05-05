package com.example.dongja94.samplegallery;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongja94 on 2015-10-06.
 */
public class ImageAdapter extends BaseAdapter {
    List<Integer> items = new ArrayList<Integer>();

    public void add(int resId) {
        items.add((Integer)resId);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (items.size() == 0) return 0;
        return Integer.MAX_VALUE;
    }

    @Override
    public Object getItem(int position) {
        return items.get(position % items.size());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView iv = null;
        if (convertView != null) {
            iv = (ImageView)convertView;
        } else {
            iv = new ImageView(parent.getContext());
            iv.setLayoutParams(new Gallery.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        iv.setImageResource(items.get(position % items.size()));
        return iv;
    }
}
