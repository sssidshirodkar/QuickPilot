package com.collge.quickpilot.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.collge.quickpilot.R;
import com.collge.quickpilot.pojo.Ride;
import com.collge.quickpilot.ui.activity.MapsActivity;
import com.collge.quickpilot.ui.activity.RideListActivity;

import java.util.ArrayList;

/**
 * Created by Siddhesh on 05-02-2017.
 */

public class RideListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_DATA = 1;
    private final int VIEW_TYPE_EMPTY = 0;

    Context context;
    ArrayList<Ride> rides;

    static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_mobile, tv_status, tv_src, tv_dest;
        public Button btnStart;

        public ViewHolder(View view) {
            super(view);
            tv_mobile = (TextView) view.findViewById(R.id.tv_mobile);
            tv_status = (TextView) view.findViewById(R.id.tv_status);
            tv_src = (TextView) view.findViewById(R.id.tv_src);
            tv_dest = (TextView) view.findViewById(R.id.tv_dest);
            btnStart = (Button) view.findViewById(R.id.btnStart);
        }

    }

    static class EmptyViewHolder extends RecyclerView.ViewHolder {
        public EmptyViewHolder(View view) {
            super(view);
        }
    }

    public RideListAdapter(Context context, ArrayList<Ride> rides) {
        this.context = context;
        this.rides = rides;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        if(viewType == VIEW_TYPE_DATA) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_list_adapter, viewGroup, false);
            viewHolder = new ViewHolder(view);
        }else{
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_empty_list_adapter, viewGroup, false);
            viewHolder = new EmptyViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        if (viewHolder.getItemViewType() == VIEW_TYPE_DATA) {
            ViewHolder holder = (ViewHolder) viewHolder;
            final Ride ride = rides.get(position);
            holder.tv_mobile.setText(ride.getMyMobile());
            holder.tv_status.setText(String.valueOf(ride.getStatus()));
            holder.tv_src.setText(ride.getSrcText());
            holder.tv_dest.setText(ride.getDestText());
            holder.btnStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //((RideListActivity) context).changeRideStatus(ride.getMyMobile());

                    Intent intent = new Intent(context, MapsActivity.class);
                    intent.putExtra("ride", ride);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(rides.size() == 0){
            return 1;
        }else {
            return rides.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (rides.size() == 0) {
            return VIEW_TYPE_EMPTY;
        }else{
            return VIEW_TYPE_DATA;
        }
    }

}
