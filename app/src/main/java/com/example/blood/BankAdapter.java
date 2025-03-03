package com.example.blood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class BankAdapter extends RecyclerView.Adapter<BankAdapter.BankViewHolder> {

    private List<BloodBank> bankList;
    private Context context;

    // Constructor
    public BankAdapter(List<BloodBank> bankList, Context context) {
        this.bankList = bankList;
        this.context = context;
    }

    @NonNull
    @Override
    public BankViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bank, parent, false);
        return new BankViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BankViewHolder holder, int position) {
        BloodBank bank = bankList.get(position);
        holder.bankName.setText(bank.getName());
        holder.bankAddress.setText(bank.getAddress());
        holder.bankContact.setText(bank.getContact());
    }

    @Override
    public int getItemCount() {
        return bankList.size();
    }

    // ViewHolder class
    public static class BankViewHolder extends RecyclerView.ViewHolder {
        TextView bankName, bankAddress, bankContact;

        public BankViewHolder(@NonNull View itemView) {
            super(itemView);
            bankName = itemView.findViewById(R.id.tvBankName);
            bankAddress = itemView.findViewById(R.id.tvBankAddress);
            bankContact = itemView.findViewById(R.id.tvBankContact);
        }
    }
}
