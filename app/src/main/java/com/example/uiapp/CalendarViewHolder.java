package com.example.uiapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CalendarViewHolder extends RecyclerView.ViewHolder {
    TextView text;
    public CalendarViewHolder(@NonNull View itemView) {
        super(itemView);
         text = itemView.findViewById(R.id.calendarNumber);
    }
}
