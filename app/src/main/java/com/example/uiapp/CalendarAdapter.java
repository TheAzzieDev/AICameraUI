package com.example.uiapp;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {

    Context context;
    List<Item> items;
    public CalendarAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items =  items;
    }
    @NonNull
    @Override
    public CalendarAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarAdapter holder, int position) {

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
