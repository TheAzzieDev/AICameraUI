package com.example.uiapp;

import static android.app.PendingIntent.getActivity;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.Intent;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;


public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.gallery);
        // Initialize the headers array with your header TextViews
        View scrollView = findViewById(R.id.scrollView);
        LinearLayout lin = findViewById(R.id.linearLayout);
        Log.d("drawable_test3", Integer.toString(((ViewGroup) lin).getChildCount()));
        

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







    /*

     ( ((WidgetType) linearLayout)).getChildCount()         antal children i widget
     ((ViewGroup) linearLayout).getChildAt(index);          child på index i widget
      Widget.getId();                                      Id från child i widget ger int
      .getTop()                                            .getTop() returnerar int av distans från toppen i förhållande till parent widget

      ViewGroup
      .removeView(View view)
      .removeViewAt(int index)

       måste testa runt med funktionen nedan
      .getLocationOnScreen(location);

     */
    private void handleStickyHeader() {
        TextView stickyHeader = findViewById(R.id.stickyHeader);
        View scrollView = findViewById(R.id.scrollView);

        int scrollY = scrollView.getScrollY();
        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        ArrayList<TextView> childViewData = new ArrayList<>();

        for (int i = 0; i < ((ViewGroup) linearLayout).getChildCount(); i++) {
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
            LinearLayout lin = findViewById(R.id.topLinearLayout);
            //String s = String.format(Locale.ENGLISH, "count: %d distance to Top: %d scrollview getTop: %d", count, headerTop, lin.getTop()) ;
            //Log.d("Distance", s);

            //https://developer.android.com/reference/android/content/res/Resources
            /*
            kolla upp funktioner
            ibland är det tydligt hur man avnänder dem, om inte fråga internet.

            Kolla upp basic fuinktioner för några widget om det går segt hitta en tutorial och följ den oldschool app med java exempelvis.

             */
            LinearLayout gallerLin = findViewById(R.id.imageLayout);
            ImageView image = (ImageView) gallerLin.getChildAt(0);
            Drawable d = image.getDrawable();

            Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.anime_sky, null);
            image.setImageDrawable(drawable);

            ImageView image2 = (ImageView) gallerLin.getChildAt(2);
            //getResources().getResourceEntryName(R.drawable.anime_sky);

            //context är egentligen bara ett sätt att komma åt olika klasser in android systemet
            //det finns this - aktiviteten, getApplicationContext(), .getContext() - från en view kan exempelvis vara textView
            //Använd till så stor grad som möjligt enbart getApplicationContext()

            Context tets = scrollView.getContext();

            int color = ContextCompat.getColor(this, R.color.main);
            stickyHeader.setTextColor(color);

            //Log.d("drawable_test", d.toString());
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
