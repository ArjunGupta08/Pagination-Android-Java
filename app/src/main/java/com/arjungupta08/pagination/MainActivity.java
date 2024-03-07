package com.arjungupta08.pagination;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AbsListView;

import com.arjungupta08.pagination.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBinding;

    private Boolean isScrolling = false;
    ArrayList<Integer> list;
    SimpleAdapter simpleAdapter;
    private int currentItems, scrolledOutItems, totalItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(5);
        list.add(6);
        list.add(4);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(5);
        list.add(6);
        list.add(4);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(5);
        list.add(6);
        list.add(4);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(5);
        list.add(6);
        list.add(4);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mainBinding.recycler.setLayoutManager(layoutManager);
        simpleAdapter = new SimpleAdapter(list);
        mainBinding.recycler.setAdapter(simpleAdapter);

        mainBinding.recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                currentItems = layoutManager.getChildCount();
                scrolledOutItems = layoutManager.findFirstVisibleItemPosition();
                totalItems = layoutManager.getItemCount();

                if (isScrolling && currentItems + scrolledOutItems == totalItems) {
                    // Fetch Data
                    isScrolling = false;
                    fetchData();
                }
            }
        });
    }

    private void fetchData() {
        mainBinding.progressBar.setVisibility(View.VISIBLE);
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> {
            for (int i = 0; i < 5; i++) {
                list.add(new Random().nextInt());
                simpleAdapter.notifyDataSetChanged();
                mainBinding.progressBar.setVisibility(View.GONE);
            }
        },2000);
    }

}