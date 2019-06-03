package com.downbeat.downbeat.Home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.downbeat.downbeat.Login.Login;
import com.downbeat.downbeat.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeMainActivity extends AppCompatActivity {

    boolean opened;
    LinearLayout viewParent;
    ImageButton button;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);

        firebaseAuth = FirebaseAuth.getInstance();

        viewParent = findViewById(R.id.viewParent);
        button = findViewById(R.id.button);

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override

            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // if user is null launch login activity
                    startActivity(new Intent(getApplicationContext(), Login.class));
                    finish();
                }else{
                    Toast.makeText(HomeMainActivity.this, "Hello "+user.getDisplayName(), Toast.LENGTH_SHORT).show();
                }
            }
        };

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swipeMenu();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override

            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // if user is null launch login activity
                    startActivity(new Intent(getApplicationContext(), Login.class));
                    finish();
                }else{
                    Toast.makeText(HomeMainActivity.this, "Hello "+user.getDisplayName(), Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    void swipeMenu(){
        if(opened){
            TranslateAnimation animate = new TranslateAnimation(
                    0,
                    0,
                    -viewParent.getHeight() + 50,
                    0);
            animate.setDuration(500);
            animate.setFillAfter(true);
            button.setRotation((float) 0);
            viewParent.startAnimation(animate);

        } else {
            TranslateAnimation animate = new TranslateAnimation(
                    0,
                    0,
                    0,
                    -viewParent.getHeight() + 50
            );
            animate.setDuration(500);
            animate.setFillAfter(true);
            button.setRotation((float) 180);
            viewParent.startAnimation(animate);
        }
        opened = !opened;
    }
}
