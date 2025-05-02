package Adapters;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import Classes.FirebaseHelper;
import Classes.House;
import com.example.houserentalmanagment.R;

import Classes.Rental_Receiver;
import Classes.Request;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class HouseAdapter extends RecyclerView.Adapter<HouseAdapter.HouseViewHolder> {
    private List<House>houseList;
    private Context context;


    public HouseAdapter(List<House> houseList) {
        this.houseList = houseList;
    }

    @NonNull
    @Override
    public HouseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.house_item,parent,false);
        context=parent.getContext();
        return new HouseViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull HouseViewHolder holder, int position) {
        House house=houseList.get(position);
        holder.Price.setText(house.getPrice());
        holder.Rooms.setText(house.getRooms());
        holder.Location.setText(house.getLocation());
        holder.Phone.setText(house.getPhone());
        if(house.isRented()){
            holder.itemView.setVisibility(View.GONE);
        }
        else{
            holder.itemView.setVisibility(View.VISIBLE);
        }
        holder.Rent.setOnClickListener(v -> {
            CreatDialog(house);

        });




    }

    @Override
    public int getItemCount() {
        return houseList.size();
    }
    private void sendRequsets(Request request) {
        FirebaseHelper firebaseHelper=new FirebaseHelper();
        firebaseHelper.addRequest(request);
    }
    private void scheduleNotification(Context context){
        Intent intent=new Intent(context, Rental_Receiver.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_IMMUTABLE);;
        AlarmManager alarmManager =(AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        long triggerTime = System.currentTimeMillis() + 2000;
        if(alarmManager!=null){
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,triggerTime,pendingIntent);
        }

    }

    public void CreatDialog( House house){
        final String[] CurrentOwnerId = new String[1];
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        View view=LayoutInflater.from(context).inflate(R.layout.requsets_dialog,null);
        builder.setView(view);
        AlertDialog dialog=builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button Send=view.findViewById(R.id.Send);
        Button Cancel=view.findViewById(R.id.Cancel);
        TextInputEditText etRenterI;
        etRenterI=view.findViewById(R.id.RenterId);
        String RenterId=etRenterI.getText().toString().trim();
        Send.setOnClickListener(v -> {
            Request request1 = new Request(house.getOwnerId(), house.getHouseId(),RenterId);
            sendRequsets(request1);
            DocumentReference Owner=db.collection("Houses").document(house.getHouseId());
            Owner.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if(documentSnapshot.exists()){
                        CurrentOwnerId[0] =documentSnapshot.getString("houseId");
                    }
                }
            });
            db.collection("Requests").whereEqualTo("OwnerId", CurrentOwnerId[0]).addSnapshotListener((snapshot, error) -> {
                if(error!=null||snapshot==null){
                    return;
                }
                for(DocumentChange dc:snapshot.getDocumentChanges()){
                   if(dc.getType()==DocumentChange.Type.ADDED){
                       scheduleNotification(context);
                   }


                }

            });

            dialog.dismiss();

        });
        Cancel.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }



    public static class HouseViewHolder extends RecyclerView.ViewHolder{
        private TextView Price,Location,Rooms,Phone,IsRented;
        private Button Rent;

        public HouseViewHolder(@NonNull View itemView) {
            super(itemView);
            Phone=itemView.findViewById(R.id.Phone2);
            Price=itemView.findViewById(R.id.Price2);
            Location=itemView.findViewById(R.id.Location2);
            Rooms=itemView.findViewById(R.id.Rooms);
            IsRented=itemView.findViewById(R.id.IsRented2);
            Rent=itemView.findViewById(R.id.Rent);
        }
    }
}
