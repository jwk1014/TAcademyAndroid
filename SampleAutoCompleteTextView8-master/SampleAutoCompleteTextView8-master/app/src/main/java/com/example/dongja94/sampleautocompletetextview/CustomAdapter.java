package com.example.dongja94.sampleautocompletetextview;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongja94 on 2015-10-06.
 */
public class CustomAdapter extends BaseAdapter implements Filterable {
    List<Photo> items = null;
    List<Photo> origItems = new ArrayList<Photo>();

    public void add(Photo item) {
        origItems.add(item);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (items == null) {
            return origItems.size();
        }
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        if (items == null) {
            return origItems.get(position);
        }
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
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

    MyFilter mFilter = new MyFilter();

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    class MyFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults result = new FilterResults();
            List<Photo> items = new ArrayList<Photo>();
            if (constraint != null && constraint.length() > 0) {
                String text = constraint.toString().toLowerCase();
                for (Photo p : origItems) {
                    String item = p.toString().toLowerCase();
                    if (item.contains(text)) {
                        items.add(p);
                    }
                }
            }
            result.values = items;
            result.count = items.size();

            return result;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            items = (List<Photo>) results.values;
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }

        }
    }
}
