package com.tunaPasta17.calendar;

import android.view.View;
import android.widget.TextView;

import com.tunaPasta17.R;

import androidx.recyclerview.widget.RecyclerView;

public class OuterRecycleViewHolder extends RecyclerView.ViewHolder {
    TextView txtMonth;

    public OuterRecycleViewHolder(View itemView) {
        super(itemView);
        txtMonth = itemView.findViewById(R.id.plan_time_txt_month);
    }

    public void doBindData(MonthTimeEntity timeEntity) {
        String title = itemView.getContext().getString(R.string.outer_title, String.valueOf(timeEntity.year), String.valueOf(timeEntity.month + 1));
        txtMonth.setText(title);
    }
}
