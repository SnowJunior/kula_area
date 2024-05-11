package com.nyabwana.kula_area;

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

public class DashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StaticRvAdapter staticRvAdapter;

    List<DynamicRVModel> items = new ArrayList<>();
    DynamicRVAdapter dynamicRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);

        ArrayList<StaticRvModel> item = new ArrayList<>();
        item.add(new StaticRvModel(R.drawable.ramen, "Cuisine"));
        item.add(new StaticRvModel(R.drawable.price, "Value"));
        item.add(new StaticRvModel(R.drawable.clock, "Time"));
        item.add(new StaticRvModel(R.drawable.location, "Area"));

        recyclerView = findViewById(R.id.recycle_1);
        staticRvAdapter = new StaticRvAdapter(item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(staticRvAdapter);

        items.add(new DynamicRVModel("Time"));
        items.add(new DynamicRVModel("Time"));
        items.add(new DynamicRVModel("Time"));
        items.add(new DynamicRVModel("Time"));
        items.add(new DynamicRVModel("Time"));
        items.add(new DynamicRVModel("Time"));
        items.add(new DynamicRVModel("Time"));
        items.add(new DynamicRVModel("Time"));
        items.add(new DynamicRVModel("Time"));

        RecyclerView drv = findViewById(R.id.recycle_2);
        drv.setLayoutManager(new LinearLayoutManager(this));
        dynamicRVAdapter = new DynamicRVAdapter(drv, this,items);
        drv.setAdapter(dynamicRVAdapter);

        dynamicRVAdapter.setLoadMore(new LoadMore() {
            @Override
            public void onLoadMore() {
                if (items.size()<=10){
                    items.add(null);
                    dynamicRVAdapter.notifyItemInserted(items.size()-1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            items.remove(items.size()-1);
                            dynamicRVAdapter.notifyItemRemoved(items.size());

                            int index = items.size();
                            int end = index+10;

                            for (int i  = index; i < end; i++) {
                                String name = UUID.randomUUID().toString();
                                DynamicRVModel item = new DynamicRVModel(name);
                                items.add(item);
                            }
                            dynamicRVAdapter.notifyDataSetChanged();
                            dynamicRVAdapter.setLoaded();
                        }
                    }, 4000);
                }
                else {
                    Toast.makeText(DashboardActivity.this, "Data Completed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}