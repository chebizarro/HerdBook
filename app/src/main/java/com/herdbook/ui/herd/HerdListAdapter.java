package com.herdbook.ui.herd;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.herdbook.data.models.Herd;

public class HerdListAdapter extends ListAdapter<Herd, HerdViewHolder> {

     public HerdListAdapter(@NonNull DiffUtil.ItemCallback<Herd> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public HerdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return HerdViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull HerdViewHolder holder, int position) {
        Herd current = getItem(position);
        holder.bind(current.getHerdName());
    }

    static class HerdDiff extends DiffUtil.ItemCallback<Herd> {

        @Override
        public boolean areItemsTheSame(@NonNull Herd oldItem, @NonNull Herd newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Herd oldItem, @NonNull Herd newItem) {
            return false;
        }
    }
}
