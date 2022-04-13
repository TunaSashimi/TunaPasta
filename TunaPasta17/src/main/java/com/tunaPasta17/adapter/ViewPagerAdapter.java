package com.tunaPasta17.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tunaPasta17.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder> {
    private List<Integer> colors = new ArrayList<>();

    {
        colors.add(android.R.color.black);
        colors.add(android.R.color.holo_red_dark);
        colors.add(android.R.color.holo_green_dark);
        colors.add(android.R.color.holo_orange_dark);
        colors.add(android.R.color.holo_purple);
        colors.add(android.R.color.holo_green_light);
        colors.add(android.R.color.holo_blue_bright);
        colors.add(android.R.color.holo_green_light);
    }

    //
    private boolean isCanLoop = true;

    @Override
    public ViewPagerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewPagerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpager2itemtest, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewPagerViewHolder holder, int position) {

        int realPosition = getRealPosition(position);

        holder.mTvTitle.setText("item " + realPosition);
        holder.mContainer.setBackgroundResource(colors.get(realPosition));
    }

    @Override
    public int getItemCount() {
        if (isCanLoop && colors.size() > 1) {
            return Integer.MAX_VALUE ;
        } else {
            return colors.size();
        }
    }

    //
    private int getRealPosition(int position) {
        if (colors.size() == 0) {
            return 0;
        } else {
            return (position + colors.size()) % colors.size();
        }
    }

    class ViewPagerViewHolder extends RecyclerView.ViewHolder {
        TextView mTvTitle;
        RelativeLayout mContainer;

        public ViewPagerViewHolder(View itemView) {
            super(itemView);
            mContainer = itemView.findViewById(R.id.container);
            mTvTitle = itemView.findViewById(R.id.tvTitle);
        }
    }
}