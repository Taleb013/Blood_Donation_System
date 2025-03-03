package com.example.blood;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvName, tvAge, tvBloodType, tvEmail, tvPhone;
    private Button btnEditProfile, btnLogout;
    private ImageView ivProfilePicture;

    // Declare ActivityResultLauncher
    private ActivityResultLauncher<Intent> editProfileLauncher;

    // SharedPreferences for storing data
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("UserProfile", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        tvName = findViewById(R.id.tvName);
        tvAge = findViewById(R.id.tvAge);
        tvBloodType = findViewById(R.id.tvBloodType);
        tvEmail = findViewById(R.id.tvEmail);
        tvPhone = findViewById(R.id.tvPhone);
        ivProfilePicture = findViewById(R.id.ivProfilePicture);
        btnEditProfile = findViewById(R.id.btnEditProfile);
        btnLogout = findViewById(R.id.btnLogout);

        // Load user data from SharedPreferences
        loadUserProfile();

        // Initialize ActivityResultLauncher
        editProfileLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        // Update Profile with new data from EditProfileActivity
                        Intent data = result.getData();
                        String updatedName = data.getStringExtra("name");
                        String updatedEmail = data.getStringExtra("email");
                        String updatedPhone = data.getStringExtra("phone");

                        // Update UI and save the changes to SharedPreferences
                        tvName.setText(updatedName);
                        tvEmail.setText(updatedEmail);
                        tvPhone.setText(updatedPhone);

                        // Save updated data to SharedPreferences
                        saveUserProfile(updatedName, updatedEmail, updatedPhone);
                    }
                });

        // Set listener for Edit Profile button
        btnEditProfile.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
            intent.putExtra("name", tvName.getText().toString());
            intent.putExtra("email", tvEmail.getText().toString());
            intent.putExtra("phone", tvPhone.getText().toString());
            editProfileLauncher.launch(intent);
        });

        // Set listener for Logout button
        btnLogout.setOnClickListener(v -> {
            // You can clear SharedPreferences or handle logout logic here
            // Clear saved data if necessary
            editor.clear();
            editor.apply();

            // Exit the app
            finishAffinity();  // Close all activities and exit the app
            System.exit(0);    // Exit the application
        });
    }

    private void loadUserProfile() {
        // Load data from SharedPreferences
        String name = sharedPreferences.getString("name", "Taleb");
        String email = sharedPreferences.getString("email", "2002mdabutaleb@gmail.com");
        String phone = sharedPreferences.getString("phone", "01718013208");

        // Set loaded data to TextViews
        tvName.setText(name);
        tvEmail.setText(email);
        tvPhone.setText(phone);
    }

    private void saveUserProfile(String name, String email, String phone) {
        // Save updated data in SharedPreferences
        editor.putString("name", name);
        editor.putString("email", email);
        editor.putString("phone", phone);
        editor.apply();  // Save asynchronously
    }
}
