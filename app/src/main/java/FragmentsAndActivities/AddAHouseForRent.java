package FragmentsAndActivities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import Adapters.HouseAdapter;
import Adapters.RequsetsAdapter;
import Classes.FirebaseHelper;
import Classes.House;
import Classes.Request;

import com.example.houserentalmanagment.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.BuildConfig;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AddAHouseForRent extends Fragment {
    private TextInputEditText etNumber,etLocation,etRooms,etPrice,etHouseId,etOwnerId;
    private String Location,Phone,Rooms,Price,HouseId,OwnerId;
    private Button Add;
    private FirebaseHelper firebaseHelper;
    private FirebaseFirestore db;
    private House house;
    RecyclerView recyclerView;
    RequsetsAdapter requsetsAdapter;
    List<Request> requestList;
    Request request;
    private Button Allrequsets,edit;


    public AddAHouseForRent() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view=inflater.inflate(R.layout.fragment_add_a_house_for_rent, container, false);
        etLocation=view.findViewById(R.id.Location);
        etNumber=view.findViewById(R.id.PhonN);
        etPrice=view.findViewById(R.id.price);
        etRooms=view.findViewById(R.id.Rooms);
        Add=view.findViewById(R.id.AddTheHouse);
        etHouseId=view.findViewById(R.id.HouseId);
        etOwnerId=view.findViewById(R.id.OwnerId);
        Allrequsets=view.findViewById(R.id.allRequsets);
        edit=view.findViewById(R.id.Edit);
        db=FirebaseFirestore.getInstance();
       firebaseHelper=new FirebaseHelper();
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Location=etLocation.getText().toString();
                Phone=etNumber.getText().toString().trim();
                Rooms=etRooms.getText().toString().trim();
                Price=etPrice.getText().toString().trim();
                HouseId=etHouseId.getText().toString().trim();
                OwnerId=etOwnerId.getText().toString().trim();
                house=new House(Location,Rooms,Phone,Price,HouseId,OwnerId);
                firebaseHelper.addHouse(house);

            }
        });
        Allrequsets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.Requsest.setVisibility(View.VISIBLE);
                MainActivity.AddHouseForRent.setVisibility(View.INVISIBLE);
                MainActivity.Log.setVisibility(View.INVISIBLE);
                MainActivity.SignUp.setVisibility(View.INVISIBLE);
                MainActivity.RentHouse.setVisibility(View.INVISIBLE);
                MainActivity.Home.setVisibility(View.INVISIBLE);

            }
            });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowEditDialog();
            }

        });

        return view;
    }

    @SuppressLint("MissingInflatedId")
    private void ShowEditDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        View dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_edit_house, null);
        builder.setView(dialogView);
        TextInputEditText etPhone,etLocation,etRooms,etPrice,etHouseId,etOwnerId;
        Button edit,cancel;
        AlertDialog dialog=builder.create();
        etPhone=dialogView.findViewById(R.id.PhonN);
        etLocation=dialogView.findViewById(R.id.Location);
        etRooms=dialogView.findViewById(R.id.Rooms);
        etPrice=dialogView.findViewById(R.id.price);
        etHouseId=dialogView.findViewById(R.id.HouseId);
        etOwnerId=dialogView.findViewById(R.id.OwnerId);
        edit=dialogView.findViewById(R.id.edit);
        cancel=dialogView.findViewById(R.id.cancel);


        Toast.makeText(getContext(), "if you dont want to edit just write the same information you have wrote before", Toast.LENGTH_SHORT).show();
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> updates = new HashMap<>();
                updates.put("Phone number", etPhone.getText().toString());
                updates.put("price", Integer.parseInt(etPrice.getText().toString()));
                updates.put("Location", etLocation.getText().toString());
                updates.put("Rooms", Integer.parseInt(etRooms.getText().toString()));
                updates.put("HouseId", etHouseId.getText().toString());
                updates.put("OwnerId", etOwnerId.getText().toString());

                FirebaseFirestore.getInstance()
                        .collection("houses")
                        .document(house.getHouseId())
                        .update(updates)
                        .addOnSuccessListener(aVoid ->
                                Toast.makeText(requireContext(), "Edit Done ", Toast.LENGTH_SHORT).show())
                        .addOnFailureListener(e ->
                                Toast.makeText(requireContext(),"Error", Toast.LENGTH_SHORT).show());

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dialog.dismiss();
            }
        });



       dialog.show();
    }


    }

