package com.example.uiapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class YearTest extends Fragment implements YearAdapter.OnItemListener{
    public List<YearItem> yearItems = new ArrayList<YearItem>();
    private RecyclerView recyclerViewYear;
    YearAdapter adapter;
    View view;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.year_test, container, false);
        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.test), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerViewYear = view.findViewById(R.id.recyclerViewYear);
        adapter = new YearAdapter( this, yearItems, view);
        yearItems.add(new YearItem("2018", "5"));
        yearItems.add(new YearItem("2018", "5"));
        yearItems.add(new YearItem("2018", "5"));

        recyclerViewYear.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewYear.setAdapter(adapter);
        return view;
    }
    @Override
    public void onYearClick(int position) {
        String message = "selected Date ";
        Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
