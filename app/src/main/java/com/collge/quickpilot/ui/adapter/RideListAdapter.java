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
import com.collge.quickpilot.firebase_database.RidesReference;
import com.collge.quickpilot.pojo.Ride;
import com.collge.quickpilot.ui.activity.MapsActivity;
import com.collge.quickpilot.ui.activity.RideListActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Siddhesh on 05-02-2017.
 */

public class RideListAdapter extends RecyclerView.Adapter<RideListAdapter.ViewHolder>  {

    Context context;
    ArrayList<Ride> rides;

    static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tv_mobile, tv_status;
        public Button btnStart;

        public ViewHolder(View view){
            super(view);
            tv_mobile = (TextView) view.findViewById(R.id.tv_mobile);
            tv_status = (TextView) view.findViewById(R.id.tv_status);
            btnStart = (Button) view.findViewById(R.id.btnStart);
        }

    }

    public RideListAdapter(Context context, ArrayList<Ride> rides){
        this.context = context;
        this.rides = rides;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_list_adapter,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Ride ride = rides.get(position);
        holder.tv_mobile.setText(ride.getMyMobile());
        holder.tv_status.setText(String.valueOf(ride.getStatus()));
        holder.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((RideListActivity)context).changeRideStatus(ride.getMyMobile());

                Intent intent = new Intent(context, MapsActivity.class);
                intent.putExtra("ride", ride);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rides.size();
    }

}
