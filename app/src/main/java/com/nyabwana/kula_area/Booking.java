package com.nyabwana.kula_area;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Booking extends AppCompatActivity {
    EditText bookingName,bookingMobile,bookingDate,bookingCapacity,bookingOccasion;
    FirebaseAuth mAuth;
    FirebaseFirestore firebaseFirestore;
    Button bookingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_booking);

        bookingName = findViewById(R.id.editFullName);
        bookingMobile = findViewById(R.id.editPhoneNumber);
        bookingOccasion = findViewById(R.id.editOccassion);
        bookingCapacity = findViewById(R.id.people_capacity);
        bookingDate = findViewById(R.id.taskPickDate);
        bookingButton = findViewById(R.id.addBookingBtn);
        mAuth = FirebaseAuth.getInstance();

        setUpDateInputs();

        bookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createBooking();
            }
        });

    }

    private void  setUpDateInputs(){
        // Start Date Picker
        bookingDate.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // Get current hour and minute
            int hour = calendar.get(Calendar.HOUR);
            int minute = calendar.get(Calendar.MINUTE);

            // Create DatePickerDialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(Booking.this, (datePicker, selectedYear, selectedMonth, selectedDay) -> {
                // Create TimePickerDialog after date selection
                TimePickerDialog timePickerDialog = new TimePickerDialog(Booking.this, (timePicker, selectedHour, selectedMinute) -> {
                    // Update bookingDate text with both date and time
                    bookingDate.setText(String.format(Locale.getDefault(), "%02d/%02d/%d %02d:%02d", selectedDay, selectedMonth + 1, selectedYear, selectedHour, selectedMinute));
                }, hour, minute, true); // Set 24 hour format to true
                timePickerDialog.show();
            }, year, month, day);
            datePickerDialog.show();
        });

    }

    private void createBooking() {
        String name = bookingName.getText().toString().trim();
        String mobile = bookingMobile.getText().toString().trim();
        String selectedDate = bookingDate.getText().toString().trim();
        String capacity = bookingCapacity.getText().toString().trim();
        String occasion = bookingOccasion.getText().toString().trim();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        assert firebaseUser != null;
        String userId = firebaseUser.getUid();

        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.US);

        try {
            Date pickedDate = dateTimeFormat.parse(selectedDate);

            BookingModel bookingModel = new BookingModel();
            bookingModel.setFullName(name);
            bookingModel.setPhoneNumber(mobile);
            bookingModel.setPickDate(pickedDate);
            bookingModel.setCapacity(capacity);
            bookingModel.setOccasion(occasion);
            bookingModel.setUserId(userId);
            bookingModel.setStatus("Pending");

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("java_bookings").add(bookingModel).addOnSuccessListener(documentReference -> {
                String bookingId =  documentReference.getId();
                bookingModel.setBookingId(bookingId);

                db.collection("java_bookings").document(bookingId).set(bookingModel).addOnSuccessListener(aVoid -> {
                    Toast.makeText(this, "Booking successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Booking.this, DashboardActivity.class);
                    startActivity(intent);
                    finish();
                }).addOnFailureListener(e -> Toast.makeText(this, "Error: Booking failed", Toast.LENGTH_SHORT).show());
            }).addOnFailureListener(e -> Toast.makeText(this, "Error: Booking failed", Toast.LENGTH_SHORT).show());
        } catch (ParseException e) {
            Toast.makeText(this, "Error parsing date", Toast.LENGTH_SHORT).show();
        }


    }
}