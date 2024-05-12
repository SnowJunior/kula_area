package com.nyabwana.kula_area;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nyabwana.kula_area.DRVinterface.LoadMore;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DashboardActivity extends AppCompatActivity implements UpdateRecyclerView{

    private RecyclerView recyclerView, recyclerView2;
    private StaticRvAdapter staticRvAdapter;

    ArrayList<DynamicRVModel> items = new ArrayList<>();
    DynamicRVAdapter dynamicRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);

        ArrayList<StaticRvModel> item = new ArrayList<>();
        item.add(new StaticRvModel(R.drawable.ramen, "Fries"));
        item.add(new StaticRvModel(R.drawable.price, "Value"));
        item.add(new StaticRvModel(R.drawable.clock, "Time"));
        item.add(new StaticRvModel(R.drawable.location, "Area"));

        recyclerView = findViewById(R.id.recycle_1);
        staticRvAdapter = new StaticRvAdapter(item, DashboardActivity.this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(staticRvAdapter);

        items = new ArrayList<>();

        recyclerView2 = findViewById(R.id.recycle_2);
        dynamicRVAdapter = new DynamicRVAdapter(items);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView2.setAdapter(dynamicRVAdapter);

    }

    @Override
    public void callback(int position, ArrayList<DynamicRVModel> items) {
        dynamicRVAdapter = new DynamicRVAdapter(items);
        dynamicRVAdapter.notifyDataSetChanged();
        recyclerView2.setAdapter(dynamicRVAdapter);

    }
}