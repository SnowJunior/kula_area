package com.nyabwana.kula_area;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class LoginTabFragment extends Fragment {

    EditText email,password;
    FirebaseAuth mAuth;
    FirebaseFirestore firebaseFirestore;
    Button loginButton;
    float v=0;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);

        email = root.findViewById(R.id.login_email);
        password = root.findViewById(R.id.login_password);
        loginButton = root.findViewById(R.id.login_button);

        email.setTranslationX(800);
        password.setTranslationX(800);
        loginButton.setTranslationX(800);

        email.setAlpha(v);
        password.setAlpha(v);
        loginButton.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        loginButton.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
                Intent intent = new Intent(getActivity(), DashboardActivity.class);
                startActivity(intent);
                getActivity().getSupportFragmentManager().beginTransaction().remove(LoginTabFragment.this).commit();
            }
        });
        return root;
    }

    private void loginUser() {
        String newEmail = email.getText().toString().trim();
        String newPassword = password.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(newEmail, newPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    Log.d("Activity login", "Login successful");
                    if (user != null) {
                        fetchUserRole(user.getUid());
                    }
                } else  {
                    Toast.makeText(getActivity(), "Login failed:" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void fetchUserRole(String userId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("java_users").document(userId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot snapshot = task.getResult();
                    if (snapshot != null && snapshot.exists()) {
                        String role = snapshot.getString("role");
                        navigateBasedOnRole(role);
                    } else {
                        Toast.makeText(getActivity(), "User Role doesn't exist", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Failed to fetch user role", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void navigateBasedOnRole(String role) {
        if ("manager".equals(role)) {
            Intent intent = new Intent(getActivity(), ManagerActivity.class);
            startActivity(intent);
        } else if ("user".equals(role)) {
            Intent intent = new Intent(getActivity(), DashboardActivity.class);
            startActivity(intent);
        }
    }
}
