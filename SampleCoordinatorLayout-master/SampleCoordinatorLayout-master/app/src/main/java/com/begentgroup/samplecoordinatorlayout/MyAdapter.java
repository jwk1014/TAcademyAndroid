package com.begentgroup.samplecoordinatorlayout;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongja94 on 2015-09-20.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }

        public void setText(String text) {
            ((TextView)itemView).setText(text);
        }
    }
    List<String> items = new ArrayList<String>();
    public void add(String item) {
        items.add(item);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(android.R.layout.simple_list_item_1, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.setText(items.get(i));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
