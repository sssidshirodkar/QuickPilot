package com.collge.quickpilot.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.collge.quickpilot.R;

import java.util.List;

/**
 * Created by Siddhesh on 05-02-2017.
 */

public class RideListAdapter extends RecyclerView.Adapter<RideListAdapter.ViewHolder>  {

    Context context;
    List<String> list;

    static class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View view){
            super(view);
        }

    }

    public RideListAdapter(Context context){
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_list_adapter,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

    }

    @Override
    public int getItemCount() {
        return /*list.size()*/5;
    }

}
