package com.nyabwana.kula_area;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileActivity extends AppCompatActivity {
    ImageView profileImage;
    TextView profileName,profileMobile;
    Button profileBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        profileImage = findViewById(R.id.profile_picture);
        profileName = findViewById(R.id.user_name);
        profileMobile = findViewById(R.id.user_number);
        profileBackButton = findViewById(R.id.back_button);

        profileBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, DashboardActivity.class);
                startActivity(intent);
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
                        String mobile = documentSnapshot.getString("email");

                        profileName.setText(userName);
                        profileMobile.setText(mobile);
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