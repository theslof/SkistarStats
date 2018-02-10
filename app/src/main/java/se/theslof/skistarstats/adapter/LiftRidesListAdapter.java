package se.theslof.skistarstats.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import se.theslof.skistarstats.databinding.LiftItemBinding;
import se.theslof.skistarstats.model.LiftRide;

public class LiftRidesListAdapter extends RecyclerView.Adapter<LiftRidesListAdapter.ViewHolder> {
    private List<LiftRide> liftRides;

    public LiftRidesListAdapter(List<LiftRide> liftRides) {
        this.liftRides = liftRides;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LiftItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false).getRoot();

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LiftRide liftRide = liftRides.get(position);
        holder.binding.setViewModel(liftRide);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return liftRides.size();
    }

    public void setList(List<LiftRide> liftRides)
    {
        this.liftRides = liftRides;
        this.notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public LiftItemBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);

            //Store binder reference for view
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
