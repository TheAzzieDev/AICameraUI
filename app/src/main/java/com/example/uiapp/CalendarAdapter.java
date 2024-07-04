package com.example.uiapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {
    private final ArrayList<String> daysOfMonth;
    private final OnItemListener onItemListener;
    private Context context;
    int selectMonth;
    int year;
    public CalendarAdapter(ArrayList<String> daysOfMonth, OnItemListener onItemListener, Context contextInput, int month, int year) {
        this.daysOfMonth = daysOfMonth;
        this.onItemListener = onItemListener;
        this.context = contextInput;
        this.selectMonth = month;
        this.year = year;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_item, parent, false); //slänger in vår layout och gör om den till en View
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.166666666); //sägger till att varje item är 1/6 i höjd av parenten
        return new CalendarViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        Log.d("debug_cal", "before");
        holder.dayOfMonth.setText(daysOfMonth.get(position));

        //vid problem med jämnförelser kolla alltid vilken typ so
        if(!daysOfMonth.get(position).isEmpty()){
            if(Integer.parseInt(daysOfMonth.get(position)) == (LocalDate.now()).getDayOfMonth()
                    && selectMonth == (LocalDate.now()).getMonthValue() && year == (LocalDate.now()).getYear()) {
                //holder.dayOfMonth.setBackgroundColor(ContextCompat.getColor(context, R.color.solidGreen));
                holder.dayOfMonth.setBackground(ContextCompat.getDrawable(context, R.drawable.calendar_button));
                holder.dayOfMonth.setPadding(45, 30, 45, 30);
                holder.dayOfMonth.setTextColor(ContextCompat.getColor(context, R.color.white));
                Log.d("calendar_test", daysOfMonth.get(position).getClass()+ " " + (LocalDate.now()).getDayOfMonth());
            }
        }
    }

    //Har ingen anning hur nedanstående egentligen fungerar, sök upp på google
    public interface OnItemListener {
        void onItemClick(int position, String dayText);
    }
    @Override
    public int getItemCount() {
        return daysOfMonth.size();
    }
}
