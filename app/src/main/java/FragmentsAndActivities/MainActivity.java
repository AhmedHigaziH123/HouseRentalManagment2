package FragmentsAndActivities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.houserentalmanagment.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    public static FrameLayout Home,Log,SignUp,RentHouse,AddHouseForRent,Requsest;
    public static HomeFrag home ;
    public static LogIn log;
    public static FragmentsAndActivities.SignUp signup;
    public static RentAHouse rentahouse;
    public static AddAHouseForRent addhouseforrent;
    public static RequsetsFragment requsetsFragment;
    private BottomNavigationView bottomNavigationView;
    FirebaseAuth auth=FirebaseAuth.getInstance();
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    FirebaseUser firebaseUser=auth.getCurrentUser();


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
        Requsest=findViewById(R.id.req);
        bottomNavigationView=findViewById(R.id.ButtonNav);
        StartFragments();
        FirebaseAuth auth=FirebaseAuth.getInstance();
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        FirebaseUser firebaseUser=auth.getCurrentUser();





    }

    private void StartFragments() {
        log=new LogIn();
        home=new HomeFrag();
        signup=new SignUp();
        rentahouse=new RentAHouse();
        addhouseforrent=new AddAHouseForRent();
        requsetsFragment=new RequsetsFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.SignUp_frame,signup).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.Login_frame,log).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.Home_frame,home).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.RentHouse_frame,rentahouse).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.AddAHouseForRent_frame,addhouseforrent).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.req,requsetsFragment).commit();
        Log.setVisibility(View.INVISIBLE);
        SignUp.setVisibility(View.INVISIBLE);
        Home.setVisibility(View.VISIBLE);
        RentHouse.setVisibility(View.INVISIBLE);
        AddHouseForRent.setVisibility(View.INVISIBLE);
        Requsest.setVisibility(View.INVISIBLE);

    }




}
