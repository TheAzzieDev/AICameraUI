
package com.example.uiapp;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;

import android.widget.Button;

import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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
                break;
        }
    }
    public void changeToGallery(View view){
        Intent intent = new Intent(MainActivity.this, GalleryActivity.class);
        startActivity(intent);
    }
}