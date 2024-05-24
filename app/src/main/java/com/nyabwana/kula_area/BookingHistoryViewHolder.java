package com.nyabwana.kula_area;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BookingHistoryViewHolder extends RecyclerView.ViewHolder {
    TextView rest_name,rest_phone,rest_person_name,rest_capacity,rest_occasion, rest_date;
    public BookingHistoryViewHolder(@NonNull View itemView) {
        super(itemView);
        rest_name = itemView.findViewById(R.id.bh_rest_name);
        rest_phone = itemView.findViewById(R.id.bh_rest_phone);
        rest_person_name = itemView.findViewById(R.id.bh_person_fullname);
        rest_capacity = itemView.findViewById(R.id.bh_rest_capacity);
        rest_occasion = itemView.findViewById(R.id.bh_rest_occasion);
        rest_date = itemView.findViewById(R.id.bh_rest_date);
    }
}
