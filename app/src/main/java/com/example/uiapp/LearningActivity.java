package com.example.uiapp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
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

    public Dialog monthDialog;

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
        Window window = dialog.getWindow();

        //magic fix
        if (window != null) {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT); // Set the width to match_parent and height to wrap_content
        }

        //removes the shitty standard background for dialogs
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        View close = dialog.findViewById(R.id.close_calendar);

        close.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

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
        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this, dialog.getContext(), selectedDate.getMonthValue(), selectedDate.getYear());
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
        int dayOfMonth = firstOfMonth.getDayOfMonth();
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();


        //ändra gärna till något finare
        int count = 0;
        for(int i = 0; i < 7; i++){
            if(i < dayOfWeek - 1){
                daysInMonthArray.add("");
                count++;
            }
        }
        for(int i = 1; i < daysInMonth + (42 - count - daysInMonth); i++) {
              if(i <= daysInMonth){
                daysInMonthArray.add(String.valueOf(i));
              }
              else{
                  daysInMonthArray.add("");
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

    /*

      monthDialog = new Dialog(this);
        monthDialog.setCancelable(true);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        monthDialog.setContentView(R.layout.calendar_month);
        ImageView close = monthDialog.findViewById(R.id.close_calendar);
        close.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        initMonthWidgets();
    */
    public void openMonthSelection(View view) {
        dialog.dismiss();
        monthDialog = new Dialog(this);
        monthDialog.setCancelable(true);
        monthDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        monthDialog.setContentView(R.layout.calendar_month);
        Window window = monthDialog.getWindow();
        if (window != null) {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT); // Set the width to match_parent and height to wrap_content
        }
        monthDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        monthDialog.show();
        initWidget();

    }

    void initWidget(){
        ViewGroup monthGrid = monthDialog.findViewById(R.id.monthLayout);
        int monthGridLength = monthGrid.getChildCount();

        for(int i = 0; i < monthGridLength; i++){
            Button child = (Button)monthGrid.getChildAt(i);
            final String text = child.getText().toString();
            Log.d("button", child.getText().toString());
            child.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    monthOnClick(text);
                }
            });
        }
    }

    public void monthOnClick(String month) {
        String text = "I am a message " + month;
        Toast.makeText(monthDialog.getContext(), text, Toast.LENGTH_SHORT).show();
        int monthIndex;

        switch (month) {
            case "Jan":
                monthIndex = 0; // January
                break;
            case "Feb":
                monthIndex = 1; // February
                break;
            case "Mar":
                monthIndex = 2; // March
                break;
            case "Apr":
                monthIndex = 3; // April
                break;
            case "May":
                monthIndex = 4; // May
                break;
            case "Jun":
                monthIndex = 5; // June
                break;
            case "Jul":
                monthIndex = 6; // July
                break;
            case "Aug":
                monthIndex = 7; // August
                break;
            case "Sep":
                monthIndex = 8; // September
                break;
            case "Oct":
                monthIndex = 9; // October
                break;
            case "Nov":
                monthIndex = 10; // November
                break;
            case "Dec":
                monthIndex = 11; // December
                break;
            default:
                // Handle invalid month abbreviation
                monthIndex = -1; // Invalid month
                break;
        }
        monthDialog.dismiss();
        selectedDate = selectedDate.withMonth(monthIndex + 1);
        setMonthView();
        dialog.show();

    }

}
