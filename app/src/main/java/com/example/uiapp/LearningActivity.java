package com.example.uiapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class LearningActivity extends AppCompatActivity {

    //never use find view outside the methods if you are trying to make the variables global
    //it throws an error becuase the layout has not yet loaded
    MyAdapter adapter;
    public RecyclerView recyclerView;
    public List<Item> items = new ArrayList<Item>();
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

    }
}
