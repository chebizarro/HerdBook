package com.herdbook.ui.herd;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.herdbook.R;

public class HerdViewHolder extends RecyclerView.ViewHolder {

    //private final TextView herdItemViw;

    public HerdViewHolder(@NonNull View itemView) {
        super(itemView);

    }

    public void bind(String text) {

    }

    static HerdViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.herd_fragment, parent,false);
        return new HerdViewHolder(view);
    }
}
