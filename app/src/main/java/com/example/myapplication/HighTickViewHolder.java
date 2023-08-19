package com.example.myapplication;

import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.io.Console;
import java.time.LocalTime;

public class HighTickViewHolder extends RecyclerView.ViewHolder {
    private final TextView m_textView;

    public HighTickViewHolder(View itemView) {
        super(itemView);
        m_textView = itemView.findViewById(R.id.tick_text);
    }

    public void bind(int position) {
        m_textView.setText(String.valueOf(position));
    }
}
