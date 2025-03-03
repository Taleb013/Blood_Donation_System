package com.example.blood.extra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.blood.R;
import java.util.List;

public class DonorAdapter extends RecyclerView.Adapter<DonorAdapter.DonorViewHolder> {

    private List<Donor> donorList;

    public DonorAdapter(List<Donor> donorList) {
        this.donorList = donorList;
    }

    @NonNull
    @Override
    public DonorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donor, parent, false);
        return new DonorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonorViewHolder holder, int position) {
        Donor donor = donorList.get(position);
        holder.phone.setText(donor.getPhone());
//        holder.dob.setText(donor.getDob());
//        holder.weight.setText(donor.getWeight());
        holder.address.setText(donor.getAddress());
        holder.gender.setText(donor.getGender());
        holder.bloodGroup.setText(donor.getBloodGroup());
    }

    @Override
    public int getItemCount() {
        return donorList.size();
    }

    public static class DonorViewHolder extends RecyclerView.ViewHolder {
        TextView phone, dob, weight, address, gender, bloodGroup;

        public DonorViewHolder(@NonNull View itemView) {
            super(itemView);
            phone = itemView.findViewById(R.id.donorPhone);
//            dob = itemView.findViewById(R.id.donorDOB);
//            weight = itemView.findViewById(R.id.donorWeight);
            address = itemView.findViewById(R.id.donorAddress);
            gender = itemView.findViewById(R.id.donorGender);
            bloodGroup = itemView.findViewById(R.id.donorBloodGroup);
        }
    }
}
