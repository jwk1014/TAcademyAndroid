package com.example.dongja94.samplerecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongja94 on 2015-10-06.
 */
public class MyAdapter extends RecyclerView.Adapter<ViewHolder> {
    List<String> items = new ArrayList<String>();

    ViewHolder.OnItemClickListener mItemClickListener;
    public void setOnItemClickListener(ViewHolder.OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    public void add(String item) {
        items.add(item);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setName(items.get(position));
        holder.setOnItemClickListener(mItemClickListener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
