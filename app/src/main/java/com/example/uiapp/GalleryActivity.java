package com.example.uiapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
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

        int scrollY = scrollView.getScrollY();
        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        ArrayList<TextView> childViewData = new ArrayList<>();

        for (int i = 0; i < ( ((ViewGroup) linearLayout)).getChildCount(); i++) {
            View child = ((ViewGroup) linearLayout).getChildAt(i);
            if(child instanceof TextView){
                Object childId = child.getId();
                childViewData.add(findViewById((int) childId));
            }
        }
        int count = 0;
        int firstDistance = 0;
        TextView currentStickyHeader = null;
        for (TextView header : childViewData) {
            int[] location = new int[2];
            header.getLocationOnScreen(location);
            int headerTop = location[1] - scrollView.getTop();
            //stickyHeader.setText(String.format(Integer.toString(location[1]-scrollView.getTop())));
            if (count == 0) firstDistance = headerTop;
            count++;
            if (headerTop <= 0) currentStickyHeader = header;
            else if(firstDistance >= 0) stickyHeader.setText("");
            else break;
        }
        if (currentStickyHeader != null) {
            stickyHeader.setText(currentStickyHeader.getText());
        }


    }
}
