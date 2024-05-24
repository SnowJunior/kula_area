package com.nyabwana.kula_area;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.nyabwana.kula_area.DRVinterface.LoadMore;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StaticRvAdapter staticRvAdapter;

    TextView profileName;
    private RestaurantAdapter adapter;
    RecyclerView restaurantList;

    BottomNavigationView bottomNavigationView;

    FirebaseFirestore firebaseFirestore;
    CollectionReference collectionReference;

    List<DynamicRVModel> items = new ArrayList<>();
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);

        imageView = findViewById(R.id.profile_icon);
        profileName = findViewById(R.id.displayName);
        bottomNavigationView = findViewById(R.id.bottom_menu);
        fetchUserDetails();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
              int itemId = menuItem.getItemId();
              if (itemId == R.id.bottom_home) {
                  Intent intent = new Intent(DashboardActivity.this, DashboardActivity.class);
                  startActivity(intent);
              }  else if (itemId == R.id.bottom_booking) {
                  Intent intent = new Intent(DashboardActivity.this, Booking.class);
                  startActivity(intent);
              } else if (itemId == R.id.bottom_review) {
                  Intent intent = new Intent(DashboardActivity.this, ReviewsActivity.class);
              }
                  return false;
            }
        });



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, Booking.class);
                startActivity(intent);
            }
        });

        ArrayList<StaticRvModel> item = new ArrayList<>();
        item.add(new StaticRvModel(R.drawable.ramen, "Cuisine"));
        item.add(new StaticRvModel(R.drawable.price, "Value"));
        item.add(new StaticRvModel(R.drawable.clock, "Time"));
        item.add(new StaticRvModel(R.drawable.location, "Area"));

        recyclerView = findViewById(R.id.recycle_1);
        staticRvAdapter = new StaticRvAdapter(item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(staticRvAdapter);

        restaurantList = findViewById(R.id.restaurant_recycler_item);
        restaurantList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RestaurantAdapter(new ArrayList<>());
        restaurantList.setAdapter(adapter);
        restaurantList.invalidate();

        firebaseFirestore = FirebaseFirestore.getInstance();

        firebaseFirestore.collection("restaurants").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    List<Restaurant> restaurants = new ArrayList<>();
                    for(QueryDocumentSnapshot document: task.getResult()) {
                        Restaurant restaurant = document.toObject(Restaurant.class);
                        restaurants.add(restaurant);
                    }
                    adapter.setRestaurants(restaurants);
                }
            }
        });
    }

    private void fetchUserDetails() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser != null) {
            String userId = currentUser.getUid();
            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

            firebaseFirestore.collection("java_users").document(userId).get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot != null && documentSnapshot.exists()) {
                        String userName = documentSnapshot.getString("name");
                        profileName.setText(userName);
                    } else {
                        Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Failed to fetch User data", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}