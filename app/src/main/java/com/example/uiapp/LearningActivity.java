package com.example.uiapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LearningActivity extends AppCompatActivity {
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
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        List<Item> items = new ArrayList<Item>();
        items.add(new Item("John Wic", "john.wick@gmail.com", R.drawable.anime_sky));
        items.add(new Item("John Wic", "john.wick@gmail.com", R.drawable.anime_sky));
        items.add(new Item("John Wic", "john.wick@gmail.com", R.drawable.anime_sky));
        items.add(new Item("John Wic", "john.wick@gmail.com", R.drawable.anime_sky));
        items.add(new Item("John Wic", "john.wick@gmail.com", R.drawable.anime_sky));
        items.add(new Item("John Wic", "john.wick@gmail.com", R.drawable.anime_sky));
        items.add(new Item("John Wic", "john.wick@gmail.com", R.drawable.anime_sky));
        items.add(new Item("John Wic", "john.wick@gmail.com", R.drawable.anime_sky));
        items.add(new Item("John Wic", "john.wick@gmail.com", R.drawable.anime_sky));
        items.add(new Item("John Wic", "john.wick@gmail.com", R.drawable.anime_sky));
        items.add(new Item("John Wic", "john.wick@gmail.com", R.drawable.anime_sky));
        items.add(new Item("John Wic", "john.wick@gmail.com", R.drawable.anime_sky));
        items.add(new Item("John Wic", "john.wick@gmail.com", R.drawable.anime_sky));


        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), items));
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
