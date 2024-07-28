package com.example.uiapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class YearAdapter extends RecyclerView.Adapter<YearViewHolder> {
    private final OnItemListener onItemListener;
    List<YearItem> yearItems;
    public YearAdapter(OnItemListener onItemListener, List<YearItem> yearItems){
        this.onItemListener = onItemListener;
        this.yearItems = yearItems;
    }
    @NonNull
    @Override
    public YearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_item, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) parent.getHeight() * 3/18;
        layoutParams.width = (int) (parent.getWidth() * 0.8);
        return new YearViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull YearViewHolder holder, int position) {
        holder.year.setText(yearItems.get(position).getYear());
        holder.incidents.setText(yearItems.get(position).getIncidents());
    }

    public interface OnItemListener {
        void onItemClick(int position, String dayText);
    }
    @Override
    public int getItemCount() {
        return yearItems.size();
    }
}
