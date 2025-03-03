package com.example.blood;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class BankActivity extends AppCompatActivity {

    private BankAdapter bankAdapter;
    private List<BloodBank> bankList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);

        recyclerView = findViewById(R.id.recyclerViewBanks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        bankList = getDummyBanks(); // Add the list of 20 blood banks here
        bankAdapter = new BankAdapter(bankList, this);
        recyclerView.setAdapter(bankAdapter);
    }

    private List<BloodBank> getDummyBanks() {
        List<BloodBank> banks = new ArrayList<>();

        // Add 20 blood banks in Bangladesh
        banks.add(new BloodBank("Red Crescent Blood Bank", "Dhaka Medical College, Dhaka", "01700000001"));
        banks.add(new BloodBank("Sandhani Central Blood Bank", "Chittagong Medical College, Chittagong", "01800000002"));
        banks.add(new BloodBank("Quantum Foundation Blood Bank", "Mirpur-10, Dhaka", "01900000003"));
        banks.add(new BloodBank("Lions Club Blood Bank", "Khulna Medical College, Khulna", "01600000004"));
        banks.add(new BloodBank("Thalassemia Foundation Blood Bank", "Rajshahi Medical College, Rajshahi", "01500000005"));
        banks.add(new BloodBank("Badhan Blood Bank", "Sylhet Medical College, Sylhet", "01300000006"));
        banks.add(new BloodBank("Blood Care Bangladesh", "Barisal Medical College, Barisal", "01900000007"));
        banks.add(new BloodBank("Blood Donation Club", "Rangpur Medical College, Rangpur", "01400000008"));
        banks.add(new BloodBank("Hope Blood Bank", "Mymensingh Medical College, Mymensingh", "01700000009"));
        banks.add(new BloodBank("Safe Blood Bank", "Comilla Medical College, Comilla", "01800000010"));
        banks.add(new BloodBank("City Blood Bank", "Shyamoli, Dhaka", "01911222333"));
        banks.add(new BloodBank("Rajshahi Blood Center", "Shaheed Ziaur Rahman Medical College, Bogra", "01633557799"));
        banks.add(new BloodBank("Life Line Blood Bank", "Feni Sadar Hospital, Feni", "01599887766"));
        banks.add(new BloodBank("Care and Cure Blood Bank", "Jessore Medical College, Jessore", "01300000123"));
        banks.add(new BloodBank("Emergency Blood Support", "Pabna Medical College, Pabna", "01744556677"));
        banks.add(new BloodBank("Universal Blood Bank", "Cox's Bazar Sadar Hospital, Cox's Bazar", "01877665544"));
        banks.add(new BloodBank("Bogra Blood Bank", "Shaheed Ziaur Rahman Medical College, Bogra", "01922553388"));
        banks.add(new BloodBank("Medilife Blood Bank", "Noakhali General Hospital, Noakhali", "01688994422"));
        banks.add(new BloodBank("Trust Blood Bank", "Kushtia Medical College, Kushtia", "01799887711"));
        banks.add(new BloodBank("Prime Blood Center", "Sirajganj Sadar Hospital, Sirajganj", "01833445566"));

        return banks;
    }
}
