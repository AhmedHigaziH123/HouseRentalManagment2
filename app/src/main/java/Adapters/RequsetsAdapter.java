package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.houserentalmanagment.R;

import Classes.Request;

import java.util.List;

public class RequsetsAdapter extends RecyclerView.Adapter<RequsetsAdapter.RequsetsViewHolder> {
    private List<Request> requests;
    private Context context;
    public RequsetsAdapter(List<Request> requests) {
        this.requests = requests;
    }

    @NonNull
    @Override
    public RequsetsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.requsets_item,parent,false);
        context=parent.getContext();
        return new RequsetsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequsetsViewHolder holder, int position) {
        Request request=requests.get(position);
        holder.OwnerId.setText(request.getOwnerId());
        holder.RenterId.setText(request.getRenterId());
        holder.HouseId.setText(request.getHouseId());

    }

    @Override
    public int getItemCount() {
        return requests.size();
    }

    public class RequsetsViewHolder extends RecyclerView.ViewHolder {
        public TextView OwnerId,RenterId,HouseId;
        public Button Accept;
        public RequsetsViewHolder(@NonNull View itemView) {
            super(itemView);
            OwnerId=itemView.findViewById(R.id.OwnerId2);
            RenterId=itemView.findViewById(R.id.RenterId2);
            HouseId=itemView.findViewById(R.id.HouseId2);
            Accept=itemView.findViewById(R.id.Accept);
        }
    }
}
