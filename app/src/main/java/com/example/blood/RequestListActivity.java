package com.example.blood;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class RequestListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FirebaseFirestore db;
    private FirestoreRecyclerAdapter<BloodRequest, RequestViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_list); // Make sure this layout exists

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();

        // Query to get all requests ordered by timestamp
        Query query = db.collection("requests").orderBy("timestamp", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<BloodRequest> options = new FirestoreRecyclerOptions.Builder<BloodRequest>()
                .setQuery(query, BloodRequest.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<BloodRequest, RequestViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull RequestViewHolder holder, int position, @NonNull BloodRequest model) {
                holder.bind(model);
            }

            @NonNull
            @Override
            public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_item, parent, false);
                return new RequestViewHolder(view);
            }
        };

        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public static class RequestViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameText, bloodGroupText, phoneText, addressText, reasonText;

        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.nameTextView);
            bloodGroupText = itemView.findViewById(R.id.bloodGroupTextView);
            phoneText = itemView.findViewById(R.id.phoneTextView);
            addressText = itemView.findViewById(R.id.locationTextView);
            reasonText = itemView.findViewById(R.id.reasonTextView);
        }

        void bind(BloodRequest request) {
            nameText.setText(request.getName());
            bloodGroupText.setText(request.getBloodGroup());
            phoneText.setText(request.getPhone());
            addressText.setText(request.getAddress());
            reasonText.setText(request.getReason());
        }
    }
}
