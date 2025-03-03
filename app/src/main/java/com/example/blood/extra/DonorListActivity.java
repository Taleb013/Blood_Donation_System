package com.example.blood.extra;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blood.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DonorListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DonorAdapter donorAdapter;
    private List<Donor> donorList;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        donorList = new ArrayList<>();
        donorAdapter = new DonorAdapter(donorList);
        recyclerView.setAdapter(donorAdapter);

        db = FirebaseFirestore.getInstance();
        fetchDonors();
    }

    private void fetchDonors() {
        CollectionReference donorsRef = db.collection("donors");

        donorsRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.e("Firestore", "Error fetching donors", error);
                    Toast.makeText(DonorListActivity.this, "Error loading data!", Toast.LENGTH_SHORT).show();
                    return;
                }

                donorList.clear();
                for (QueryDocumentSnapshot document : value) {
                    Donor donor = document.toObject(Donor.class);
                    donorList.add(donor);
                }
                donorAdapter.notifyDataSetChanged();
            }
        });
    }
}
