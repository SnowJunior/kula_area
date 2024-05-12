package com.nyabwana.kula_area;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StaticRvAdapter extends RecyclerView.Adapter<StaticRvAdapter.StaticRVViewHolder> {

    private ArrayList<StaticRvModel> items;
    int row_index = -1;
    UpdateRecyclerView updateRecyclerView;
    Activity activity;
    boolean check = true;
    boolean select = true;

    public StaticRvAdapter(ArrayList<StaticRvModel> items, Activity activity, UpdateRecyclerView updateRecyclerView) {
        this.items = items;
        this.activity = activity;
        this.updateRecyclerView = updateRecyclerView;
    }

    @NonNull
    @Override
    public StaticRVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.static_rv_item,parent,false);
        return new StaticRVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StaticRVViewHolder holder, @SuppressLint("RecyclerView") int position) {
        StaticRvModel currentItem = items.get(position);
        holder.imageView.setImageResource(currentItem.getImage());
        holder.textView.setText(currentItem.getText());

        if (check) {
            ArrayList<DynamicRVModel> items = new ArrayList<DynamicRVModel>();
            items.add(new DynamicRVModel("Amka Cafe", R.drawable.clock));
            items.add(new DynamicRVModel("Che Cafe", R.drawable.clock));
            items.add(new DynamicRVModel("Geco Cafe", R.drawable.clock));
            items.add(new DynamicRVModel("Kaya Cafe", R.drawable.clock));
            items.add(new DynamicRVModel("Jenga jungle", R.drawable.clock));
            items.add(new DynamicRVModel("Wine tails", R.drawable.clock));
            items.add(new DynamicRVModel("Cocoa Cafe", R.drawable.clock));

            updateRecyclerView.callback(position, items);
            check = false;
        }

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();

                if (position ==0) {
                    ArrayList<DynamicRVModel> items = new ArrayList<DynamicRVModel>();
                    items.add(new DynamicRVModel("Amka Cafe", R.drawable.clock));
                    items.add(new DynamicRVModel("Che Cafe", R.drawable.clock));
                    items.add(new DynamicRVModel("Geco Cafe", R.drawable.clock));
                    items.add(new DynamicRVModel("Kaya Cafe", R.drawable.clock));
                    items.add(new DynamicRVModel("Jenga jungle", R.drawable.clock));
                    items.add(new DynamicRVModel("Wine tails", R.drawable.clock));
                    items.add(new DynamicRVModel("Cocoa Cafe", R.drawable.clock));

                    updateRecyclerView.callback(position, items);
                }

                else if (position == 1) {
                    ArrayList<DynamicRVModel> items = new ArrayList<DynamicRVModel>();
                    items.add(new DynamicRVModel("Pizza 1", R.drawable.ramen));
                    items.add(new DynamicRVModel("Pizza 2", R.drawable.ramen));
                    items.add(new DynamicRVModel("Pizza 3", R.drawable.ramen));
                    items.add(new DynamicRVModel("Pizza 4", R.drawable.ramen));
                    items.add(new DynamicRVModel("Pizza 5", R.drawable.ramen));
                    items.add(new DynamicRVModel("Pizza 6", R.drawable.ramen));
                    items.add(new DynamicRVModel("Pizza 7", R.drawable.ramen));

                    updateRecyclerView.callback(position, items);
                }

                else if (position == 2) {
                    ArrayList<DynamicRVModel> items = new ArrayList<DynamicRVModel>();
                    items.add(new DynamicRVModel("Fries 1", R.drawable.price));
                    items.add(new DynamicRVModel("Fries 2", R.drawable.price));
                    items.add(new DynamicRVModel("Fries 3", R.drawable.price));
                    items.add(new DynamicRVModel("Fries 4", R.drawable.price));
                    items.add(new DynamicRVModel("Fries 5", R.drawable.price));
                    items.add(new DynamicRVModel("Fries 6", R.drawable.price));
                    items.add(new DynamicRVModel("Fries 7", R.drawable.price));

                    updateRecyclerView.callback(position, items);
                }

                else if (position == 3) {
                    ArrayList<DynamicRVModel> items = new ArrayList<DynamicRVModel>();
                    items.add(new DynamicRVModel("Fries 1", R.drawable.location));
                    items.add(new DynamicRVModel("Fries 2", R.drawable.location));
                    items.add(new DynamicRVModel("Fries 3", R.drawable.location));
                    items.add(new DynamicRVModel("Fries 4", R.drawable.location));
                    items.add(new DynamicRVModel("Fries 5", R.drawable.location));
                    items.add(new DynamicRVModel("Fries 6", R.drawable.location));
                    items.add(new DynamicRVModel("Fries 7", R.drawable.location));

                    updateRecyclerView.callback(position, items);
                }

                else if (position == 4) {
                    ArrayList<DynamicRVModel> items = new ArrayList<DynamicRVModel>();
                    items.add(new DynamicRVModel("Wings 1", R.drawable.ramen));
                    items.add(new DynamicRVModel("Wings 2", R.drawable.ramen));
                    items.add(new DynamicRVModel("Wings 3", R.drawable.ramen));
                    items.add(new DynamicRVModel("Wings 4", R.drawable.ramen));
                    items.add(new DynamicRVModel("Wings 5", R.drawable.ramen));
                    items.add(new DynamicRVModel("Wings 6", R.drawable.ramen));
                    items.add(new DynamicRVModel("Wings 7", R.drawable.ramen));

                    updateRecyclerView.callback(position, items);
                }
            }
        });
        if (select){
            if (position == 0)
                holder.linearLayout.setBackgroundResource(R.drawable.static_rv_selected_bg);
            select=false;
        }
        else {
            if (row_index == position) {
                holder.linearLayout.setBackgroundResource(R.drawable.static_rv_selected_bg);
            } else {
                holder.linearLayout.setBackgroundResource(R.drawable.static_rv_bg);
            }
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class StaticRVViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;
        LinearLayout linearLayout;

        public StaticRVViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_item);
            textView = itemView.findViewById(R.id.text_item);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }
}
