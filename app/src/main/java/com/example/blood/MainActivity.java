package com.example.blood;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.blood.extra.DonorListActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_LOCATION_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button donorButton = findViewById(R.id.donor);   // Donor
        donorButton.setOnClickListener(v -> {
            Intent intent1 = new Intent(MainActivity.this, DonorFormActivity.class);
            startActivity(intent1);
        });

        Button mapButton = findViewById(R.id.map);  // Map
        mapButton.setOnClickListener(v -> {
            // Check for location permissions before launching MapActivity
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                // If permission granted, open MapActivity
                Intent intent2 = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent2);
            } else {
                // Request permission
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
            }
        });

        Button seekerButton = findViewById(R.id.seeker);
        seekerButton.setOnClickListener(v -> {
      Intent intent3 = new Intent(MainActivity.this, RequestListActivity.class);
            startActivity(intent3);

        });

        Button requestButton = findViewById(R.id.request);
        requestButton.setOnClickListener(v -> {
            Intent intent4 = new Intent(MainActivity.this, RequestActivity.class);
            startActivity(intent4);
        });
        Button hospitalButton = findViewById(R.id.hospital); // Hospital
        hospitalButton.setOnClickListener(v -> {
            Intent intent5 = new Intent(MainActivity.this, HospitalActivity.class);
            startActivity(intent5);
        });

        Button bankButton = findViewById(R.id.bank);  // Bank
        bankButton.setOnClickListener(v -> {
            Intent intent6 = new Intent(MainActivity.this, BankActivity.class);
            startActivity(intent6);
        });
        Button profileButton = findViewById(R.id.profile);  // Profile
        profileButton.setOnClickListener(v -> {
            Intent intent7 = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent7);
        });
        Button faqButton = findViewById(R.id.faq);
        faqButton.setOnClickListener(v -> {
            Intent intent8 = new Intent(MainActivity.this, FaqActivity.class);
            startActivity(intent8);
        });

        Button socialButton = findViewById(R.id.social);
        socialButton.setOnClickListener(v -> {
            Intent intent9 = new Intent(MainActivity.this, SocialActivity.class);
            startActivity(intent9);
        });

        Button donorlistButton = findViewById(R.id.donorList);
        donorlistButton.setOnClickListener(v -> {
            Intent intent10 = new Intent(MainActivity.this, DonorListActivity.class);
            startActivity(intent10);
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, launch MapActivity
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            } else {
                // Handle the case where permission is denied
                Toast.makeText(this, "Location permission is required to view the map.", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
