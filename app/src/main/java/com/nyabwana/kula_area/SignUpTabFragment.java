package com.nyabwana.kula_area;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUpTabFragment  extends Fragment {

    EditText signup_username, signup_email, signup_role, signup_password, signup_confirm_password;
    Button signup_button;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth mAuth;
    Spinner spinner;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment, container, false);

        firebaseFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        signup_username = root.findViewById(R.id.signup_username);
        signup_email = root.findViewById(R.id.signup_email);
        signup_role = root.findViewById(R.id.signup_role);
        signup_password = root.findViewById(R.id.signup_password);
        signup_confirm_password = root.findViewById(R.id.confirm_password);

//        signup_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!validatePassword() | !validateEmail() | !validateConfirmPassword()) {
//                    String email = signup_email.getText().toString().trim();
//                    String name = signup_username.getText().toString().trim();
//                    String role = signup_role.getText().toString().trim();
//                    String password = signup_password.getText().toString().trim();
//                    String confirmPassword = signup_confirm_password.getText().toString().trim();
//
//                    createUser(name,email,role,password,confirmPassword);
//                }
//            }
//        });

        return root;
    }

//    private void createUser(String name, String email, final String role, String password, String confirmPassword) {
//        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()) {
//                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
//                    if (firebaseUser != null) {
//                        String userId = firebaseUser.getUid();
//                        User newUser = new User(userId,email,name,role,password,confirmPassword);
//
//                        firebaseFirestore.collection("java_users").document(userId).set(newUser).addOnSuccessListener(aVoid -> {
//                            Toast.makeText(getActivity(), "Sign up successful", Toast.LENGTH_SHORT).show();
//                        }).addOnFailureListener(e -> {
//                            Toast.makeText(getActivity(), "Failed to create Account", Toast.LENGTH_SHORT).show();
//                        });
//                    }
//                } else {
//                    Toast.makeText(getActivity(),"Authentication failed",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//    private Boolean validateEmail() {
//        String val = signup_email.getText().toString();
//        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
//        if (val.isEmpty()) {
//            signup_email.setError("Field Cannot be empty");
//            return false;
//        }  else if (!val.matches(emailPattern)) {
//            signup_email.setError("Invalid email address");
//            return false;
//        } else {
//            signup_email.setError(null);
//            signup_email.setEnabled(false);
//            return true;
//        }
//    }
//
//    private Boolean validatePassword() {
//        String val = signup_password.getText().toString();
//        String passwordVal = "^" +
//                "(?=.*[a-zA-Z])" +      //any letter
//                "(?=.*[@#$%^&+=])" +    //at least 1 special character
//                "(?=\\S+$)" +           //no white spaces
//                ".{4,}" +               //at least 4 characters
//                "$";
//        if (val.isEmpty()) {
//            signup_password.setError("Field cannot be empty");
//            return false;
//        } else if (!val.matches(passwordVal)) {
//            signup_password.setError("Password is too weak");
//            return false;
//        } else {
//            signup_password.setError(null);
//            signup_password.setEnabled(false);
//            return true;
//        }
//    }
//
//    private Boolean validateConfirmPassword() {
//        String val = signup_confirm_password.getText().toString();
//        String val2 = signup_password.getText().toString();
//
//        String confirmpasswordVal = "^" +
//                "(?=.*[a-zA-Z])" +      //any letter
//                "(?=.*[@#$%^&+=])" +    //at least 1 special character
//                "(?=\\S+$)" +           //no white spaces
//                ".{4,}" +               //at least 4 characters
//                "$";
//        if (val.isEmpty()) {
//            signup_confirm_password.setError("Field cannot be empty");
//            return false;
//        } else if (!val.matches(confirmpasswordVal)) {
//            signup_confirm_password.setError("Password too weak");
//            return false;
//        } else if (!val.equals(val2)) {
//            signup_confirm_password.setError("Passwords do not match");
//            return false;
//        } else {
//            signup_confirm_password.setError(null);
//            signup_confirm_password.setEnabled(false);
//            return true;
//        }
//    }
}
