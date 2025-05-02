package FragmentsAndActivities;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import Classes.FirebaseHelper;
import com.example.houserentalmanagment.R;
import Classes.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;


public class SignUp extends Fragment {
    private TextInputEditText etEmail, etPassword, etUserName, etPhone,etRenterId;
    private Button SignUp;
    private String UserName, email1, Phone, Pass,RenterId;

    private FirebaseHelper firebaseHelper;
    private User user;
    private String userId;
    private boolean isVisible=false;


    public SignUp() {
        // Required empty public constructor
    }


    @SuppressLint("ClickableViewAccessibility")
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
        etRenterId=view.findViewById(R.id.RenterId);
        RenterId=etRenterId.getText().toString().trim();
        email1 = etEmail.getText().toString().trim();
        Pass = etPassword.getText().toString().trim();
        Phone = etPhone.getText().toString().trim();
        UserName = etUserName.getText().toString().trim();
        firebaseHelper=new FirebaseHelper();

        etPassword.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                 int drawableRightWidth = etPassword.getCompoundDrawables()[2] != null ? etPassword.getCompoundDrawables()[2].getBounds().width() : 0;
                if (event.getRawX() >= (etPassword.getRight() - drawableRightWidth - etPassword.getPaddingEnd())) {
                    togglePasswordVisibility();
                    return true;
                }
            }
            return false;
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check() == false) {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(RenterId.isEmpty()){
                        RenterId="An Lessor";
                        user=new User(email1,Pass,UserName,Phone,RenterId);
                        firebaseHelper.addUser(user);
                    }
                    else {
                        user = new User(email1, Pass, UserName, Phone, RenterId);
                        firebaseHelper.addUser(user);
                    }
                    MainActivity.AddHouseForRent.setVisibility(View.INVISIBLE);
                    MainActivity.Log.setVisibility(View.VISIBLE);
                    MainActivity.SignUp.setVisibility(View.INVISIBLE);
                    MainActivity.RentHouse.setVisibility(View.INVISIBLE);
                    MainActivity.Home.setVisibility(View.INVISIBLE);

                }
            }
        });
        return view;

    }
    private void togglePasswordVisibility() {
        if (isVisible) {
            etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            etPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_closed_eye, 0);
        } else {
            etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            etPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_opened_eye, 0);
        }
        etPassword.setSelection(etPassword.getText().length());
        isVisible = !isVisible;
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