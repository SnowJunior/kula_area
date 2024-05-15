package com.nyabwana.kula_area;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity {

    TextView backToLogin;
    EditText signupUsername, signupEmail, signupRole, signupPassword, signupConfirm_password;
    Button registerButton;
    FirebaseAuth mAuth;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        firebaseFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        registerButton = findViewById(R.id.signup_button);
        backToLogin = findViewById(R.id.back_to_login);
        signupEmail = findViewById(R.id.signup_email);
        signupRole = findViewById(R.id.signup_role);
        signupUsername = findViewById(R.id.signup_username);
        signupPassword = findViewById(R.id.signup_password);
        signupConfirm_password = findViewById(R.id.confirm_password);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateEmail() | !validatePassword() | !validateConfirmPassword()) {
                    String email = signupEmail.getText().toString().trim();
                    String password = signupPassword.getText().toString().trim();
                    String role = signupRole.getText().toString().trim();
                    String username = signupUsername.getText().toString().trim();
                    String confirmPassword = signupConfirm_password.getText().toString().trim();

                    createUser(username,email,role,password,confirmPassword);
                }
            }
        });

        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void createUser(String name, String email, final String role, String password, String confirmPassword) {
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                    if (firebaseUser != null) {
                        String userId = firebaseUser.getUid();
                        User newUser = new User(userId,email,name,role,password,confirmPassword);

                        firebaseFirestore.collection("java_users").document(userId).set(newUser).addOnSuccessListener(aVoid -> {
                            Toast.makeText(RegisterActivity.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, DashboardActivity.class);
                            startActivity(intent);
                            finish();
                        }).addOnFailureListener(e -> {
                            Toast.makeText(RegisterActivity.this, "Failed to create Account", Toast.LENGTH_SHORT).show();
                        });
                    }
                } else {
                    Toast.makeText(RegisterActivity.this,"Authentication failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Boolean validateEmail() {
        String val = signupEmail.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            signupEmail.setError("Field Cannot be empty");
            return false;
        }  else if (!val.matches(emailPattern)) {
            signupEmail.setError("Invalid email address");
            return false;
        } else {
            signupEmail.setError(null);
            signupEmail.setEnabled(false);
            return true;
        }
    }

        private Boolean validatePassword() {
        String val = signupPassword.getText().toString();
        String passwordVal = "^" +
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";
        if (val.isEmpty()) {
            signupPassword.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            signupPassword.setError("Password is too weak");
            return false;
        } else {
            signupPassword.setError(null);
            signupPassword.setEnabled(false);
            return true;
        }
    }

    private Boolean validateConfirmPassword() {
        String val = signupConfirm_password.getText().toString();
        String val2 = signupPassword.getText().toString();

        String confirmpasswordVal = "^" +
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";
        if (val.isEmpty()) {
            signupConfirm_password.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(confirmpasswordVal)) {
            signupConfirm_password.setError("Password too weak");
            return false;
        } else if (!val.equals(val2)) {
            signupConfirm_password.setError("Passwords do not match");
            return false;
        } else {
            signupConfirm_password.setError(null);
            signupConfirm_password.setEnabled(false);
            return true;
        }
    }
}