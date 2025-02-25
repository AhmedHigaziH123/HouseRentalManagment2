package com.example.houserentalmanagment;

import static android.content.ContentValues.TAG;

import android.media.MediaCodec;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


public class SignUp extends Fragment {
    private TextInputEditText etEmail, etPassword, etUserName, etPhone;
    private Button SignUp;
    private FirebaseFirestore firestore;
    private String UserName, email1, Phone, Pass, UserID;
    private FirebaseAuth auth;


    public SignUp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        etEmail = view.findViewById(R.id.Emaila);
        etPassword = view.findViewById(R.id.Password1);
        SignUp = view.findViewById(R.id.SignUp);
        etPhone = view.findViewById(R.id.Phone);
        etUserName = view.findViewById(R.id.UserName);
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        email1 = etEmail.getText().toString().trim();
        Pass = etPassword.getText().toString().trim();
        Phone = etPhone.getText().toString().trim();
        UserName = etUserName.getText().toString().trim();
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check() == false) {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                } else {
                    auth.createUserWithEmailAndPassword(email1, Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getActivity(), "User  Created", Toast.LENGTH_SHORT).show();
                                UserID = auth.getCurrentUser().getUid();
                                DocumentReference documentReference = firestore.collection("Users").document("UserID");
                                Map<String, Object> User = new HashMap<>();
                                User.put("Name", UserName);
                                User.put("Email", email1);
                                User.put("Phone", Phone);
                                User.put("Password", Pass);
                                documentReference.set(User).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Log.d(TAG, "Your Profile is Created Successfully For" + UserID);
                                        MainActivity.AddHouseForRent.setVisibility(View.INVISIBLE);
                                        MainActivity.Log.setVisibility(View.VISIBLE);
                                        MainActivity.SignUp.setVisibility(View.INVISIBLE);
                                        MainActivity.RentHouse.setVisibility(View.INVISIBLE);
                                        MainActivity.Home.setVisibility(View.INVISIBLE);

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d(TAG, "Error");
                                    }
                                });




                            }

                        }
                    });


                }
            }
        });
        return view;
    }
    private boolean check() {
        int NumCount = 0, CharCount = 0;
        email1 = etEmail.getText().toString();
        Pass = etPassword.getText().toString();
        UserName=etUserName.getText().toString();
        Phone=etPhone.getText().toString();
        if (email1.isEmpty()) {
            etEmail.setError("Email is Required");
            return false;
        }
        if (!(Patterns.EMAIL_ADDRESS.matcher(email1).matches())) {
            etEmail.setError("Enter valid Email address !");
            return false;

        }
        if (Pass.isEmpty()) {
            etPassword.setError("Password is Required");
            return false;
        }
        for (int i = 0; i < Pass.length(); i++) {
            if (Pass.charAt(i) >= '0' && Pass.charAt(i) <= '9') {
                NumCount++;
            }
            if ((Pass.charAt(i) >= 'a' && Pass.charAt(i) <= 'z') || (Pass.charAt(i) >= 'A' && Pass.charAt(i) <= 'Z')) {
                CharCount++;
            }
        }
        if (NumCount < 3) {
            etPassword.setError("Password Must Contain At least 3 Numbers");
            return false;
        }
        if (CharCount < 6) {
            etPassword.setError("Password Must Contain At least 6 Characters");
            return false;
        }
        CharCount = 0;
        for (int j = 0; j < UserName.length(); j++) {
            if (UserName.charAt(j) >= 'A' && UserName.charAt(j) <= 'Z') {
                CharCount++;
            }
        }
        if (CharCount == 0) {
            etUserName.setError("Username Must Contain At least one Capital Latter");
            return false;
        }
        if(Phone.length()!=10){
            etPhone.setError("PhoneNumber Must Contain 10 Numbers");
            return false;
        }
        return true;
    }
}