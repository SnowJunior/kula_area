package com.nyabwana.kula_area;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BookingHistoryAdapter extends RecyclerView.Adapter<BookingHistoryViewHolder> {
    private List<BookingModel> booking = new ArrayList<>();

    public void setBooking(List<BookingModel> bookings) {
        this.booking.clear();
        this.booking.addAll(bookings);
        notifyDataSetChanged();
    }

    public BookingHistoryAdapter(List<BookingModel> bookings) {this.booking = bookings;}

    @NonNull
    @Override
    public BookingHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_history_item, parent, false);
        return new BookingHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingHistoryViewHolder holder, int position) {
        BookingModel bookings = booking.get(position);

        holder.rest_person_name.setText(bookings.getFullName());
        holder.rest_occasion.setText(bookings.getOccasion());
        holder.rest_capacity.setText(bookings.getCapacity());
    }

    @Override
    public int getItemCount() {
        return booking.size();
    }
}
