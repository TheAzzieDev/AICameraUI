package com.example.uiapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.gallery);
        // Initialize the headers array with your header TextViews
        View scrollView = findViewById(R.id.scrollView);
        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                handleStickyHeader();
            }
        });
    }

    public void switchActivity(View view) {
        Intent intent = new Intent(GalleryActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void handleStickyHeader() {
        TextView stickyHeader = findViewById(R.id.stickyHeader);
        View scrollView = findViewById(R.id.scrollView);

        TextView[] headers = new TextView[]{
                findViewById(R.id.header1),
                findViewById(R.id.header2),
                findViewById(R.id.header5)
                // Add more headers if you have more
        };
        int scrollY = scrollView.getScrollY();

        TextView currentStickyHeader = null;
        for (TextView header : headers) {
            int[] location = new int[2];
            header.getLocationOnScreen(location);
            int headerTop = location[1] - scrollView.getTop();
            stickyHeader.setText(String.format(Integer.toString(location[1]-scrollView.getTop())));
            if (headerTop <= 0) {
                currentStickyHeader = header;
            } else {
                break;
            }
        }
        if (currentStickyHeader != null) {
            stickyHeader.setText(currentStickyHeader.getText());
        }

    }
}
