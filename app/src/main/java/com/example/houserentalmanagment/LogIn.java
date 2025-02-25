package com.example.houserentalmanagment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LogIn extends Fragment {
    private TextInputEditText etEmail1, etPassword1;
    private CheckBox ch1, ch2;
    private Button Login1, SignUp1;
    private FirebaseAuth auth;
    private String email, pass;
    private boolean isclicked1=false,isIsclicked2=true;


    public LogIn() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_log_in, container, false);
        etEmail1 = view.findViewById(R.id.Email);
        etPassword1 = view.findViewById(R.id.Password);
        ch1 = view.findViewById(R.id.lordhome);
        ch2 = view.findViewById(R.id.Tenant);
        Login1 = view.findViewById(R.id.Login);
        SignUp1 = view.findViewById(R.id.SignUp);
        auth = FirebaseAuth.getInstance();
        if (ch2.isActivated()){
            isclicked1=false;
            isIsclicked2=true;
        }
        Login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check() == true) {
                   if(isIsclicked2==true&&isclicked1==false){
                       MainActivity.AddHouseForRent.setVisibility(View.INVISIBLE);
                       MainActivity.Log.setVisibility(View.INVISIBLE);
                       MainActivity.SignUp.setVisibility(View.INVISIBLE);
                       MainActivity.RentHouse.setVisibility(View.VISIBLE);
                       MainActivity.Home.setVisibility(View.INVISIBLE);
                   }
                   else{
                       MainActivity.AddHouseForRent.setVisibility(View.VISIBLE);
                       MainActivity.Log.setVisibility(View.INVISIBLE);
                       MainActivity.SignUp.setVisibility(View.INVISIBLE);
                       MainActivity.RentHouse.setVisibility(View.INVISIBLE);
                       MainActivity.Home.setVisibility(View.INVISIBLE);
                   }

                }
                else {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }

            }

        });

        SignUp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.AddHouseForRent.setVisibility(View.INVISIBLE);
                MainActivity.Log.setVisibility(View.INVISIBLE);
                MainActivity.SignUp.setVisibility(View.VISIBLE);
                MainActivity.RentHouse.setVisibility(View.INVISIBLE);
                MainActivity.Home.setVisibility(View.INVISIBLE);

            }
        });

        return view;
    }

    private boolean check() {
        int NumCount = 0, CharCount = 0;
        email = etEmail1.getText().toString();
        pass = etPassword1.getText().toString();
        if (email.isEmpty()) {
            etEmail1.setError("Email is Required");
          return false;
        }
        if (!(Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
            etEmail1.setError("Enter valid Email address !");
            return false;

        }
        if (pass.isEmpty()) {
            etPassword1.setError("Password is Required");
            return false;
        }
        for (int i = 0; i < pass.length(); i++) {
            if (pass.charAt(i) >= '0' && pass.charAt(i) <='9' ) {
                NumCount++;
            }
            if ((pass.charAt(i) >= 'a' && pass.charAt(i) <= 'z') || (pass.charAt(i) >= 'A' && pass.charAt(i) <= 'Z')) {
                CharCount++;
            }
        }
        if(NumCount<3){
            etPassword1.setError("Password Must Contain At least 3 Numbers");
            return false;
        }
        if (CharCount<6){
            etPassword1.setError("Password Must Contain At least 6 Characters");
            return false;
        }
        return true;
    }

}