package com.nyabwana.kula_area;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class RestaurantViewHolder extends RecyclerView.ViewHolder {
    ImageView imageUrl;
    TextView restaurant_name;
    TextView restaurant_price;
    TextView restaurant_mobile;

    public RestaurantViewHolder (View itemView) {
        super(itemView);
        restaurant_name = itemView.findViewById(R.id.recycler_restaurantName);
        imageUrl = itemView.findViewById(R.id.recycle_card_image_item);
        restaurant_price = itemView.findViewById(R.id.recycler_restaurant_price);
        restaurant_mobile = itemView.findViewById(R.id.restaurant_phone);
    }

}
