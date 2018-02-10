package se.theslof.skistarstats.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import se.theslof.skistarstats.databinding.EntityItemBinding;
import se.theslof.skistarstats.model.Entity;

/**
 * Created by theslof on 2018-02-07.
 */

public class FriendsListAdapter extends RecyclerView.Adapter<FriendsListAdapter.ViewHolder> {
    private List<Entity> skiers;

    public FriendsListAdapter(List<Entity> skiers) {
        this.skiers = skiers;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = EntityItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false).getRoot();

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Entity skier = skiers.get(position);
        holder.binding.setViewModel(skier);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return skiers.size();
    }

    public void setList(List<Entity> skiers)
    {
        this.skiers = skiers;
        this.notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public EntityItemBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);

            //Store binder reference for view
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
