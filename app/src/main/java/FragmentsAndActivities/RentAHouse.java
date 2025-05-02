package FragmentsAndActivities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import Classes.House;
import com.example.houserentalmanagment.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

import Adapters.HouseAdapter;


public class RentAHouse extends Fragment {
    RecyclerView recyclerView;
    HouseAdapter houseAdapter;
    List<House> houseList;
    FirebaseFirestore db;
    SearchView searchView;
    House house;
   Button Rent;


    public RentAHouse() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view=inflater.inflate(R.layout.fragment_rent_a_house, container, false);
        recyclerView=view.findViewById(R.id.Recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        houseList=new ArrayList<>();
        houseAdapter=new HouseAdapter(houseList);
        recyclerView.setAdapter(houseAdapter);
        recyclerView.setHasFixedSize(true);
        db=FirebaseFirestore.getInstance();
        loadHouses();
        return view;


    }
    public void loadHouses(){
        db.collection("Houses")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        houseList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            try {
                                House house = document.toObject(House.class);
                                houseList.add(house);
                            } catch (Exception e) {
                                Log.e("FireStore", "Error " + e.getMessage());
                            }
                        }
                        houseAdapter.notifyDataSetChanged();
                    } else {
                        Log.e("FireStore", "Error", task.getException());
                    }
                });
    }


}

