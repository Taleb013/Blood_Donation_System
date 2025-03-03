package com.example.blood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPhone;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        btnSave = findViewById(R.id.btnSave);

        // Retrieve the current data from ProfileActivity
        Intent intent = getIntent();
        etName.setText(intent.getStringExtra("name"));
        etEmail.setText(intent.getStringExtra("email"));
        etPhone.setText(intent.getStringExtra("phone"));

        // Handle the save button click
        btnSave.setOnClickListener(v -> {
            // Pass the updated data back to ProfileActivity
            Intent resultIntent = new Intent();
            resultIntent.putExtra("name", etName.getText().toString());
            resultIntent.putExtra("email", etEmail.getText().toString());
            resultIntent.putExtra("phone", etPhone.getText().toString());
            setResult(RESULT_OK, resultIntent);
            finish();  // Close this activity and return to ProfileActivity
        });
    }
}
