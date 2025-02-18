package com.example.houserentalmanagment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    public static FrameLayout Home,Log,SignUp,RentHouse,AddHouseForRent;
    public static HomeFrag home ;
    public static LogIn log;
    public static SignUp signup;
    public static RentAHouse rentahouse;
    public static AddAHouseForRent addhouseforrent;
    private BottomNavigationView bottomNavigationView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Home=findViewById(R.id.Home_frame);
        Log=findViewById(R.id.Login_frame);
        SignUp=findViewById(R.id.SignUp_frame);
        RentHouse=findViewById(R.id.RentHouse_frame);
        AddHouseForRent=findViewById(R.id.AddAHouseForRent_frame);
        bottomNavigationView=findViewById(R.id.ButtonNav);
        StartFragments();

    }

    private void StartFragments() {
        log=new LogIn();
        home=new HomeFrag();
        signup=new SignUp();
        rentahouse=new RentAHouse();
        addhouseforrent=new AddAHouseForRent();
        getSupportFragmentManager().beginTransaction().replace(R.id.SignUp_frame,signup).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.Login_frame,log).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.Home_frame,home).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.RentHouse_frame,rentahouse).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.AddAHouseForRent_frame,addhouseforrent).commit();
        Log.setVisibility(View.INVISIBLE);
        SignUp.setVisibility(View.INVISIBLE);
        Home.setVisibility(View.VISIBLE);
        RentHouse.setVisibility(View.INVISIBLE);
        AddHouseForRent.setVisibility(View.INVISIBLE);

    }

}
