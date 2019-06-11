package com.downbeat.downbeat.Home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.downbeat.downbeat.MainActivity;
import com.downbeat.downbeat.Meditate.MeditateActivity;
import com.downbeat.downbeat.MoodManager.MoodMainActivity;
import com.downbeat.downbeat.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeMainActivity extends AppCompatActivity {

    ImageButton downbeatIconImageButton;
    ImageButton moodIconImageButton;
    ImageButton relaxIconImageButton;
    ImageButton meditationIconImageButton;
    ImageButton chatIconImageButton;
    ImageButton helpIconImageButton;
    ImageButton blogIconImageButton;
    private FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);


        //binding goes here
        downbeatIconImageButton = findViewById(R.id.downbeatIconImageButton);
        moodIconImageButton = findViewById(R.id.moodIconImageButton);
        relaxIconImageButton = findViewById(R.id.relaxIconImageButton);
        meditationIconImageButton = findViewById(R.id.meditateIconImageButton);
        chatIconImageButton = findViewById(R.id.chatIconImageButton);
        helpIconImageButton = findViewById(R.id.helpIconImageButton);
        blogIconImageButton = findViewById(R.id.blogIconImageButton);


        //FireBase init goes here
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();


        Toast.makeText(getApplicationContext() , "Welcome, "+firebaseUser.getDisplayName() , Toast.LENGTH_SHORT).show();


        //On Click listener goes here
        downbeatIconImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                startActivity( new Intent(getApplicationContext() , MainActivity.class));
                finish();
            }
        });

        moodIconImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , MoodMainActivity.class));
            }
        });

        meditationIconImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , MeditateActivity.class));
            }
        });



    }


    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

}