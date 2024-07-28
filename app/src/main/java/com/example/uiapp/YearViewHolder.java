package com.example.uiapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class YearViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView year;
    TextView incidents;
    public YearViewHolder(@NonNull View itemView) {
        super(itemView);
        year = itemView.findViewById(R.id.Year);
        incidents = itemView.findViewById(R.id.incidentsNumber);
    }

    @Override
    public void onClick(View view) {

    }
}
