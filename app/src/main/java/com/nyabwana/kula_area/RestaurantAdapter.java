package com.nyabwana.kula_area;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantViewHolder> {
    private List<Restaurant> restaurants = new ArrayList<>();

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants.clear();
        this.restaurants.addAll(restaurants);
        notifyDataSetChanged();
    }
    public RestaurantAdapter(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new RestaurantViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        Restaurant restaurant = restaurants.get(position);
        String restaurant_image = restaurant.getImage();

        holder.restaurant_name.setText(restaurant.getName());
        holder.restaurant_mobile.setText(restaurant.getPhone());
        if (restaurant.getPrice() != null) {
            holder.restaurant_price.setText(restaurant.getPrice());
        } else {
            holder.restaurant_price.setText("Price Not Available");
        }
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }
}
