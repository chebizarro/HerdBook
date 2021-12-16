package com.herdbook.ui.herd;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.herdbook.R;
import com.herdbook.data.DAO.HerdWithAnimals;
import com.herdbook.data.models.Herd;
import com.herdbook.databinding.HerdGridFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class HerdListAdapter extends RecyclerView.Adapter<HerdListAdapter.HerdViewHolder> {

    private final HerdListSelectedListener herdListSelectedListener;
    private final List<HerdWithAnimals> data = new ArrayList<>();

    private HerdGridFragmentBinding gridFragmentBinding;

    public HerdListAdapter(HerdViewModel viewModel, LifecycleOwner lifecycleOwner, HerdListSelectedListener herdListSelectedListener) {
        this.herdListSelectedListener = herdListSelectedListener;
        viewModel.getHerds().observe(lifecycleOwner, herds -> {
            data.clear();
            if (herds != null) {
                data.addAll(herds);
                notifyDataSetChanged();
            }
        });
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public HerdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.herd_grid_item, parent, false);
        gridFragmentBinding = HerdGridFragmentBinding.inflate(LayoutInflater.from(parent.getContext()));
        View view = gridFragmentBinding.getRoot();
        return new HerdViewHolder(view, herdListSelectedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HerdViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).herd.getId();
    }

    static final class HerdViewHolder extends RecyclerView.ViewHolder {

        private HerdWithAnimals herd;

        public HerdViewHolder(@NonNull View itemView, HerdListSelectedListener herdListSelectedListener) {
            super(itemView);
        }

        void bind(HerdWithAnimals herd) {
            this.herd = herd;
        }
    }
}
