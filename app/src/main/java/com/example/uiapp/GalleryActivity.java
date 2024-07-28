package com.example.uiapp;

import static android.app.PendingIntent.getActivity;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.Intent;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;


public class GalleryActivity extends Fragment {

    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gallery, container, false);
        // Initialize the headers array with your header TextViews
        View scrollView = view.findViewById(R.id.scrollView);
        LinearLayout lin = view.findViewById(R.id.linearLayout);
        Log.d("drawable_test3", Integer.toString(((ViewGroup) lin).getChildCount()));
        

        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                handleStickyHeader(view);

            }
        });
        return inflater.inflate(R.layout.gallery, container, false);
    }

    private void handleStickyHeader(View rootView) {
        TextView stickyHeader = rootView.findViewById(R.id.stickyHeader);
        View scrollView = rootView.findViewById(R.id.scrollView);

        int scrollY = scrollView.getScrollY();
        LinearLayout linearLayout = rootView.findViewById(R.id.linearLayout);
        ArrayList<TextView> childViewData = new ArrayList<>();

        for (int i = 0; i < ((ViewGroup) linearLayout).getChildCount(); i++) {
            View child = ((ViewGroup) linearLayout).getChildAt(i);
            if(child instanceof TextView){
                Object childId = child.getId();
                childViewData.add(rootView.findViewById((int) childId));
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
            LinearLayout lin = rootView.findViewById(R.id.topLinearLayout);
            //String s = String.format(Locale.ENGLISH, "count: %d distance to Top: %d scrollview getTop: %d", count, headerTop, lin.getTop()) ;
            //Log.d("Distance", s);

            //https://developer.android.com/reference/android/content/res/Resources
            /*
            kolla upp funktioner
            ibland är det tydligt hur man avnänder dem, om inte fråga internet.

            Kolla upp basic fuinktioner för några widget om det går segt hitta en tutorial och följ den oldschool app med java exempelvis.

             */
            LinearLayout gallerLin = rootView.findViewById(R.id.imageLayout);
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

            int color = ContextCompat.getColor(getActivity().getApplicationContext(), R.color.main);
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
