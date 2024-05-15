package com.nyabwana.kula_area;

import android.content.Intent;
import android.os.Bundle;
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

public class ReviewsActivity extends AppCompatActivity {

    EditText restaurantReview;
    Button reviewSubmit;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reviews);

        firebaseFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        restaurantReview = findViewById(R.id.editTextReview);
        reviewSubmit = findViewById(R.id.review_button);

    }

    private void createReview() {
        String review = restaurantReview.getText().toString().trim();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        assert firebaseUser != null;
        String userId = firebaseUser.getUid();

        ReviewModel reviewModel = new ReviewModel();
        reviewModel.setReview(review);
        reviewModel.setUserId(userId);
        reviewModel.setRestaurantName("Amka Cafe");

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("java_reviews").add(reviewModel).addOnSuccessListener(documentReference -> {
            String reviewId = documentReference.getId();
            reviewModel.setReviewId(reviewId);

            db.collection("java_reviews").document(reviewId).set(reviewModel).addOnSuccessListener(aVoid -> {
                Toast.makeText(this, "Review posted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ReviewsActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }).addOnFailureListener(e -> Toast.makeText(this, "Error: Review failed to post", Toast.LENGTH_SHORT).show());
        }).addOnFailureListener(e -> Toast.makeText(this, "Error: Review failed to post", Toast.LENGTH_SHORT).show());
    }
}