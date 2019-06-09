package com.downbeat.downbeat.Home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.downbeat.downbeat.MainActivity;
import com.downbeat.downbeat.Meditate.MeditateActivity;
import com.downbeat.downbeat.R;
import com.google.firebase.auth.FirebaseAuth;

public class HomeMainActivity extends AppCompatActivity {

    ImageButton downbeatIconImageButton, meditateIconImageButton;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);

        firebaseAuth = FirebaseAuth.getInstance();



        downbeatIconImageButton = findViewById(R.id.downbeatIconImageButton);
        meditateIconImageButton = findViewById(R.id.meditateIconImageButton);

        firebaseAuth = FirebaseAuth.getInstance();

        meditateIconImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MeditateActivity.class));
                finish();
            }
        });
        downbeatIconImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                startActivity( new Intent(getApplicationContext() , MainActivity.class));
                finish();
            }
        });



    }


    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }


}