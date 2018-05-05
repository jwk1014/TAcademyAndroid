package com.example.dongja94.sampleautocompletetextview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Created by dongja94 on 2015-10-06.
 */
public class MyAdapter extends ArrayAdapter<Photo> {
    public MyAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemView v;
        if (convertView != null) {
            v = (ItemView)convertView;
        } else {
            v = new ItemView(parent.getContext());
        }
        v.setPhot((Photo)getItem(position));
        return v;
    }
}
