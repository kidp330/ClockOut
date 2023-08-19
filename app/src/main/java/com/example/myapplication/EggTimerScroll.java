package com.example.myapplication;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.*;

public class EggTimerScroll extends Adapter<ViewHolder> {
    private static final int VIEW_TYPE_HIGH_TICK = 0;
    private static final int VIEW_TYPE_LOW_TICK = 1;

    @Override
    public int getItemViewType(int position) {
        return position % 4 == 0 ? VIEW_TYPE_HIGH_TICK : VIEW_TYPE_LOW_TICK;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater lf = LayoutInflater.from(parent.getContext());
        return switch (viewType) {
            case VIEW_TYPE_HIGH_TICK -> new HighTickViewHolder(lf.inflate(R.layout.high_tick, parent, false));
            case VIEW_TYPE_LOW_TICK  -> new LowTickViewHolder( lf.inflate(R.layout.low_tick,  parent, false));
            default -> throw new IllegalArgumentException("Invalid view type");
        };
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_HIGH_TICK)
            ((HighTickViewHolder) holder).bind(position);
    }


    @Override
    public int getItemCount() {
        return 100;
    }



}
