package com.example.uiapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class LearningActivity extends AppCompatActivity  implements CalendarAdapter.OnItemListener{

    //never use find view outside the methods if you are trying to make the variables global
    //it throws an error becuase the layout has not yet loaded
    MyAdapter adapter;
    public RecyclerView recyclerView;
    public List<Item> items = new ArrayList<Item>();

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;
    public Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.learning_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.learning_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new MyAdapter(getApplicationContext(), items);

        items.add(new Item(R.drawable.anime_girl, "2024-05-23"));
        items.add(new Item(R.drawable.anime_sky, "2024-06-23"));
        items.add(new Item(R.drawable.anime_sky, "2024-06-23"));
        items.add(new Item(R.drawable.anime_sky, "2024-06-23"));
        items.add(new Item(R.drawable.anime_sky, "2024-06-23"));
        items.add(new Item(R.drawable.anime_sky, "2024-06-23"));
        items.add(new Item(R.drawable.anime_sky, "2024-06-23"));

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void removeImage(View view) {
        List<Item> itemsToRemove = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault());
        for (int i = 0; i < items.size(); i++) {
            try {
                // Parse the two dates
                Date now = new Date();
                Date imageDate = dateFormat.parse(items.get(i).getDate());
                long diffInMillis = now.getTime() - imageDate.getTime();
                long diffInDays = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
                Log.d("difference in days", Integer.toString((int) diffInDays));
                if (diffInDays > 28){
                    itemsToRemove.add(items.get(i));
                }
            } catch (ParseException e) {
                Log.d("image date is invalid", e.toString());
            }
        }
        items.removeAll(itemsToRemove);
        adapter.notifyDataSetChanged();
    }


    public void showCalendar(View view) {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.calendar_view);

        // View close = findViewById(R.id.close_calendar);
        /*
        close.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        */
        dialog.show();
        initWidgets();
        selectedDate = LocalDate.now();
        setMonthView();
    }

    private void initWidgets() {
        calendarRecyclerView = dialog.findViewById(R.id.calendarRecyclerView);
        monthYearText = dialog.findViewById(R.id.monthYearTV);
    }
    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);
        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this, dialog.getContext(), selectedDate.getMonthValue());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(dialog.getContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
        //Log.d("calendar_test", Integer.toString(selectedDate.getDayOfMonth()));
    }
    //figure this one out
    private String monthYearFromDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM yyyy");
        return date.format(formatter);
    }
    //this one as well
    private ArrayList<String> daysInMonthArray(LocalDate date){
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);
        int daysInMonth = yearMonth.lengthOfMonth();

        //some error with getValue
        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfMonth();

        for(int i = 0; i < 42; i++){
            if(i <= dayOfWeek || i > daysInMonth + dayOfWeek){
                daysInMonthArray.add("");
            }
            else {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }
        return daysInMonthArray;
    }
    public void previousMonthAction(View view){
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }
    public void nextMonthAction(View view){
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(int position, String dayText) {
        if(!dayText.isEmpty()){
            String message = "selected Date " + dayText + " " + monthYearFromDate(selectedDate);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
}
