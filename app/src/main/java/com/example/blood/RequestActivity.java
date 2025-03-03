package com.example.blood;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class RequestActivity extends AppCompatActivity {
    private EditText nameEditText, phoneEditText, addressEditText, reasonEditText;
    private Spinner bloodGroupSpinner;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        db = FirebaseFirestore.getInstance();
        nameEditText = findViewById(R.id.etName);
        bloodGroupSpinner = findViewById(R.id.spinnerBloodGroup);
        phoneEditText = findViewById(R.id.etPhone);
        addressEditText = findViewById(R.id.etAddress);
        reasonEditText = findViewById(R.id.etReason);

        findViewById(R.id.btnSubmit).setOnClickListener(v -> submitRequest());
    }

    private void submitRequest() {
        String name = nameEditText.getText().toString().trim();
        String bloodGroup = bloodGroupSpinner.getSelectedItem().toString();
        String phone = phoneEditText.getText().toString().trim();
        String address = addressEditText.getText().toString().trim();
        String reason = reasonEditText.getText().toString().trim();

        if (name.isEmpty() || phone.isEmpty() || address.isEmpty() || reason.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!phone.matches("\\d{11}")) {
            Toast.makeText(this, "Enter a valid 11-digit phone number", Toast.LENGTH_SHORT).show();
            return;
        }

        if (bloodGroup.equals("Select Blood Group")) {
            Toast.makeText(this, "Please select a blood group", Toast.LENGTH_SHORT).show();
            return;
        }

        findViewById(R.id.btnSubmit).setEnabled(false);

        Map<String, Object> request = new HashMap<>();
        request.put("name", name);
        request.put("bloodGroup", bloodGroup);
        request.put("phone", phone);
        request.put("address", address);
        request.put("reason", reason);
        request.put("timestamp", FieldValue.serverTimestamp());

        db.collection("requests")
                .add(request)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(RequestActivity.this, "Request submitted successfully", Toast.LENGTH_SHORT).show();
                    Intent intent101 = new Intent(RequestActivity.this, MainActivity.class);
                    startActivity(intent101);
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(RequestActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    findViewById(R.id.btnSubmit).setEnabled(true);
                });
    }
}
