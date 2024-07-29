package com.example.uiapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class YearViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
    TextView year;
    TextView incidents;
    private final YearAdapter.OnItemListener onItemListener;

    public YearViewHolder(@NonNull View itemView, YearAdapter.OnItemListener onItemListener) {
        super(itemView);
        year = itemView.findViewById(R.id.Year);
        incidents = itemView.findViewById(R.id.incidentsNumber);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        //apperently getAdapterPosition is deprecated, I dont really know reason for it yet, gotta search it out
        onItemListener.onYearClick(getBindingAdapterPosition());
    }
    public void setMargins(int left, int top, int right, int bottom) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) itemView.getLayoutParams();
        layoutParams.setMargins(left, top, right, bottom);
        itemView.setLayoutParams(layoutParams);
    }
}
