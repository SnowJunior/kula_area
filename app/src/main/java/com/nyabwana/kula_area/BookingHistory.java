package com.nyabwana.kula_area;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class BookingHistory extends AppCompatActivity {

    RecyclerView bh_recycler;
    private BookingHistoryAdapter adapter;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_booking_history);

        bh_recycler = findViewById(R.id.booking_history_view);
        bh_recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BookingHistoryAdapter(new ArrayList<>());
        bh_recycler.setAdapter(adapter);
        bh_recycler.invalidate();

        firebaseFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        String userId = mAuth.getCurrentUser().getUid();

        firebaseFirestore.collection("java_bookings").document(userId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String userCred = documentSnapshot.getString("userId");
                fetchUserBookings(userCred);
            }
        });

    }
    private void fetchUserBookings(String userId) {
                firebaseFirestore.collection("java_bookings").whereEqualTo("userId", userId).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            List<BookingModel> bookingModels = new ArrayList<>();
                            for (QueryDocumentSnapshot document: task.getResult()){
                                BookingModel bookingModel = document.toObject(BookingModel.class);
                                bookingModels.add(bookingModel);
                            }
                            adapter.setBooking(bookingModels);
                        }
                    }
                });
    }
}