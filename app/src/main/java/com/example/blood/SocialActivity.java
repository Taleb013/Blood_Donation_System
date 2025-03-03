package com.example.blood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class SocialActivity extends AppCompatActivity {

    private ImageButton btnFacebook, btnLinkedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);

        btnFacebook = findViewById(R.id.btnFacebook);
        btnLinkedIn = findViewById(R.id.btnLinkedIn);

        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSocialMedia("https://www.facebook.com/profile.php?id=100078076509424");
            }
        });

        btnLinkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSocialMedia("https://www.linkedin.com/in/md-abu-taleb-876285260/");
            }
        });
    }

    private void openSocialMedia(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, android.net.Uri.parse(url));
        startActivity(browserIntent);
    }
}
