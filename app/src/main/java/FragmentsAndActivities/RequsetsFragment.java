package FragmentsAndActivities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.houserentalmanagment.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

import Adapters.RequsetsAdapter;
import Classes.House;
import Classes.Request;

public class RequsetsFragment extends Fragment {
    private RecyclerView allRequsets;
    private RequsetsAdapter requsetsAdapter;
    private List<Request> requests;
    private FirebaseFirestore db;
    private Request request;



    public RequsetsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view=inflater.inflate(R.layout.fragment_requsets, container, false);
        allRequsets=view.findViewById(R.id.allRequsets);
        requests=new ArrayList<>();
        allRequsets.setLayoutManager(new LinearLayoutManager(getContext()));
        requsetsAdapter=new RequsetsAdapter(requests);
        allRequsets.setAdapter(requsetsAdapter);
        db=FirebaseFirestore.getInstance();
        loadRequsets();
        return view;
    }
    public void loadRequsets(){
        db.collection("Requests")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    requests.clear();
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        Request request = doc.toObject(Request.class);
                        requests.add(request);
                    }
                    requsetsAdapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> {
                    // يمكنك عرض رسالة خطأ هنا
                });
    }
}