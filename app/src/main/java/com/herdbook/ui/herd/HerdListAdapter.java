package com.herdbook.ui.herd;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;

import com.herdbook.BR;
import com.herdbook.R;
import com.herdbook.data.DAO.HerdWithAnimals;
import com.herdbook.data.models.Animal;
import com.herdbook.data.models.Herd;
import com.herdbook.ui.BaseDataBoundAdapter;
import com.herdbook.ui.DataBoundViewHolder;

import java.util.ArrayList;
import java.util.List;

public class HerdListAdapter extends BaseDataBoundAdapter {

    private final HerdListActionCallback mActionCallback;

    private final List<Object> mItems = new ArrayList<>();

    public HerdListAdapter(HerdViewModel viewModel,
                           LifecycleOwner lifecycleOwner,
                           HerdListActionCallback actionCallback) {

        viewModel.getHerds().observe(lifecycleOwner, herds -> {
            mItems.clear();
            if (herds != null) {
                Log.i("INFO", "Herd count: " + herds.size() );
                for (HerdWithAnimals herd : herds) {
                    mItems.add(herd.herd);
                    mItems.addAll(herd.animals);
                }
                notifyDataSetChanged();
            }
        });
        this.mActionCallback = actionCallback;
    }

    @Override
    protected void bindItem(DataBoundViewHolder holder, int position, List payloads) {
        holder.binding.setVariable(BR.data, mItems.get(position));
        holder.binding.setVariable(BR.callback, mActionCallback);
    }


    @Override
    public int getItemLayoutId(int position) {
        // use layout ids as types
        Object item = getItem(position);
        if (item instanceof Herd) {
            return R.layout.herd_group_header;
        }
        if (item instanceof Animal) {
            return R.layout.animal_grid_item;
        }
        throw new IllegalArgumentException("Unknown item type " + item);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public Object getItem(int position) {
        return mItems.get(position);
    }

    public void addItem(Object item) {
        mItems.add(item);
        notifyItemInserted(mItems.size() - 1);
    }

    public void addItem(int position, Object item) {
        mItems.add(position, item);
        notifyItemInserted(position);
    }

}
