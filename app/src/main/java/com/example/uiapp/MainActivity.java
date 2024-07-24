
package com.example.uiapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

import android.widget.Button;

import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.uiapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button button = findViewById(R.id.stream_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });


    }
    public void toggleOptions(View view) {
        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LearningActivity.class);
        startActivity(intent);
    }

    public void showText(View view) {
        int id = view.getId();
        Button myButton = findViewById(R.id.stream_button);
        String buttonText = myButton.getText().toString();

        switch (id){
            case R.id.stream_button:
                Toast.makeText(this, "whta", Toast.LENGTH_SHORT).show();
                break;
            case R.id.configure_button:
                Toast.makeText(this, "hool", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, CalendarTest.class);
                startActivity(intent);
                break;
        }
    }
    public void changeToGallery(View view){
        Intent intent = new Intent(MainActivity.this, GalleryActivity.class);
        startActivity(intent);
    }

    public void navClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.camera:
                binding.camera.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.selectorGreen));
                binding.camera.setImageTintList(ContextCompat.getColorStateList(this, R.color.lightGreen));
                binding.gallery.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.lightGreen));
                binding.settings.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.lightGreen));
                break;

            case R.id.gallery:
                binding.camera.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.lightGreen));
                binding.camera.setImageTintList(ContextCompat.getColorStateList(this, R.color.solidGreen));
                binding.gallery.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.selectorGreen));
                binding.settings.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.lightGreen));
                break;
            case R.id.settings:
                binding.camera.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.lightGreen));
                binding.camera.setImageTintList(ContextCompat.getColorStateList(this, R.color.solidGreen));
                binding.gallery.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.lightGreen));
                binding.settings.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.selectorGreen));
                break;
        }
    }



}