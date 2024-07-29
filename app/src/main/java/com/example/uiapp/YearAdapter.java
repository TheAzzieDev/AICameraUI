package com.example.uiapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class YearAdapter extends RecyclerView.Adapter<YearViewHolder> {
    private final OnItemListener onItemListener;
    List<YearItem> yearItems;
    View testView;
    public YearAdapter(OnItemListener onItemListener, List<YearItem> yearItems, View testView){
        this.onItemListener = onItemListener;
        this.yearItems = yearItems;
        this.testView = testView;
    }
    @NonNull
    @Override
    public YearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(testView.getContext());
        View view = inflater.inflate(R.layout.year_item, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) parent.getHeight() * 6/25;
        return new YearViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull YearViewHolder holder, int position) {
        int topMargin = 5;
        int bottomMargin = 5;
        holder.setMargins(0, topMargin, 0, bottomMargin);
        holder.year.setText(yearItems.get(position).getYear());
        holder.incidents.setText(yearItems.get(position).getIncidents());
    }

    public interface OnItemListener {
        void onYearClick(int position);
    }
    @Override
    public int getItemCount() {
        return yearItems.size();
    }
}
