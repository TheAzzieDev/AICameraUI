package com.example.uiapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class YearTest extends Fragment implements YearAdapter.OnItemListener{
    public List<YearItem> yearItems = new ArrayList<YearItem>();
    private RecyclerView recyclerViewYear;
    YearAdapter adapter;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.year_test, container, false);


        recyclerViewYear = view.findViewById(R.id.recyclerViewYear);
        adapter = new YearAdapter( this, yearItems);
        yearItems.add(new YearItem("2018", "5"));

        recyclerViewYear.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        recyclerViewYear.setAdapter(adapter);
        return view;
    }
    @Override
    public void onItemClick(int position, String dayText) {
        String message = "selected Date ";
        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
