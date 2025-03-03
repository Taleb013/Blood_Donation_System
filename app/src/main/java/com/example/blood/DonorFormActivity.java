package com.example.blood;

import com.example.blood.extra.DonorListActivity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.blood.extra.DonorDatabaseHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DonorFormActivity extends AppCompatActivity {

    private DonorDatabaseHelper dbHelper;
    private EditText phoneNumber, dateOfBirth, weight, address;
    private Spinner bloodGroupSpinner;
    private RadioGroup genderGroup;
    private Button submitButton;
    private FirebaseFirestore dbc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_form);

        // Initialize Database Helper
        dbHelper = new DonorDatabaseHelper(this);

        // Initialize UI Components
        phoneNumber = findViewById(R.id.phoneNumber);
        dateOfBirth = findViewById(R.id.dateOfBirth);
        weight = findViewById(R.id.weight);
        address = findViewById(R.id.addressText);
        bloodGroupSpinner = findViewById(R.id.bloodGroupSpinner);
        genderGroup = findViewById(R.id.genderGroup);
        submitButton = findViewById(R.id.submitButton);

        // Blood Group Spinner Setup
        String[] bloodGroups = {"Select Blood Group", "A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, bloodGroups);
        bloodGroupSpinner.setAdapter(adapter);

        // Date Picker for Date of Birth
        dateOfBirth.setFocusable(false);
        dateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(DonorFormActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                                dateOfBirth.setText(selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        // Submit Button Click Listener
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDonorData();
            }
        });
    }

    private void saveDonorData() {
        String phone = phoneNumber.getText().toString().trim();
        String dob = dateOfBirth.getText().toString().trim();
        String donorWeight = weight.getText().toString().trim();
        String donorAddress = address.getText().toString().trim();
        String bloodGroup = bloodGroupSpinner.getSelectedItem().toString();

        // Get selected gender
        int selectedGenderId = genderGroup.getCheckedRadioButtonId();
        RadioButton selectedGender = findViewById(selectedGenderId);
        String gender = (selectedGender != null) ? selectedGender.getText().toString() : "";

        // Validation
        if (phone.isEmpty() || dob.isEmpty() || donorWeight.isEmpty() || donorAddress.isEmpty() || gender.isEmpty() || bloodGroup.equals("Select Blood Group")) {
            Toast.makeText(this, "Please fill all fields correctly", Toast.LENGTH_SHORT).show();
            return;
        }

//        // Store data in SQLite
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("phone", phone);
//        values.put("dob", dob);
//        values.put("weight", donorWeight);
//        values.put("address", donorAddress);
//        values.put("gender", gender);
//        values.put("blood_group", bloodGroup);
//        long newRowId = db.insert("donors", null, values);
//        db.close();

        // Initialize Firestore
        dbc = FirebaseFirestore.getInstance();

        // Create a map to store the donor data
        Map<String, Object> donorData = new HashMap<>();
        donorData.put("phone", phone);
        donorData.put("dob", dob);
        donorData.put("weight", donorWeight);
        donorData.put("address", donorAddress);
        donorData.put("gender", gender);
        donorData.put("bloodGroup", bloodGroup);

        // Save the data in Firestore
        dbc.collection("donors")
                .add(donorData)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(DonorFormActivity.this, "Donor Added Successfully!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(DonorFormActivity.this, MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(DonorFormActivity.this, "Error saving donor!", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Firestore", "Error adding document", e);
                        Toast.makeText(DonorFormActivity.this, "Error saving data", Toast.LENGTH_SHORT).show();
                    }
                });

//        // Confirmation for SQLite
//        if (newRowId != -1) {
//            Toast.makeText(this, "Saved locally in SQLite!", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "SQLite save failed!", Toast.LENGTH_SHORT).show();
//        }
    }
}
