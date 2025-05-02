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

import com.example.houserentalmanagment.R;
import com.google.android.material.textfield.TextInputEditText;


public class LogIn extends Fragment {
    private TextInputEditText etEmail1, etPassword1,etRenter,etLessor;
    private Button Login1, SignUp1;
    private String email, pass,Renter,Lessor;
    private boolean isVisible=false;


    public LogIn() {
        // Required empty public constructor
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_log_in, container, false);
        etEmail1 = view.findViewById(R.id.Email);
        etPassword1 = view.findViewById(R.id.Password);
        Login1 = view.findViewById(R.id.Login);
        SignUp1 = view.findViewById(R.id.SignUp);
        etRenter=view.findViewById(R.id.Renter);
        etLessor=view.findViewById(R.id.Lessor);
        etPassword1.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                int drawableRightWidth = etPassword1.getCompoundDrawables()[2] != null ? etPassword1.getCompoundDrawables()[2].getBounds().width() : 0;
                if (event.getRawX() >= (etPassword1.getRight() - drawableRightWidth - etPassword1.getPaddingEnd())) {
                    togglePasswordVisibility();
                    return true;
                }
            }
            return false;
        });

        Login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Renter=etRenter.getText().toString();
                Lessor=etLessor.getText().toString();
                if((Renter.equals("Renter"))&& Lessor.isEmpty()){
                    MainActivity.AddHouseForRent.setVisibility(View.INVISIBLE);
                    MainActivity.Log.setVisibility(View.INVISIBLE);
                    MainActivity.SignUp.setVisibility(View.INVISIBLE);
                    MainActivity.RentHouse.setVisibility(View.VISIBLE);
                    MainActivity.Home.setVisibility(View.INVISIBLE);

                }
                if ((Lessor.equals("Lessor"))&& Renter.isEmpty()){
                    MainActivity.AddHouseForRent.setVisibility(View.VISIBLE);
                    MainActivity.Log.setVisibility(View.INVISIBLE);
                    MainActivity.SignUp.setVisibility(View.INVISIBLE);
                    MainActivity.RentHouse.setVisibility(View.INVISIBLE);
                    MainActivity.Home.setVisibility(View.INVISIBLE);
                }
                else {
                    etRenter.setError("one of Lessor and Renter should be empty");
                    etLessor.setError("one of Lessor and Renter should be empty");
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
    private void togglePasswordVisibility() {
        if (isVisible) {
            etPassword1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            etPassword1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_closed_eye, 0);
        } else {
            etPassword1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            etPassword1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_opened_eye, 0);
        }
        etPassword1.setSelection(etPassword1.getText().length());
        isVisible = !isVisible;
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