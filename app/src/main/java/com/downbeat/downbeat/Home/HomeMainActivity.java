package com.downbeat.downbeat.Home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.downbeat.downbeat.Login.Login;
import com.downbeat.downbeat.MainActivity;
import com.downbeat.downbeat.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeMainActivity extends AppCompatActivity implements TopSlidingMenuFragment.OnFragmentInteractionListener {

    boolean opened;
    LinearLayout viewParent;
    ImageButton button;
    ImageButton downbeatIconImageButton;
    FrameLayout topSlidingMenuFragmentFrameLayout;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseAuth.AuthStateListener authListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);

        firebaseAuth = FirebaseAuth.getInstance();

        viewParent = findViewById(R.id.viewParent);
        button = findViewById(R.id.btnSlidingMenu);
        downbeatIconImageButton = findViewById(R.id.downbeatIconImageButton);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        addTopSlidingMenuFragment();

        downbeatIconImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                startActivity( new Intent(getApplicationContext() , MainActivity.class));
                finish();
            }
        });



    }

    private void addTopSlidingMenuFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        TopSlidingMenuFragment topSlidingMenuFragment = new TopSlidingMenuFragment();
        fragmentTransaction.add(R.id.topSlidingMenuFrameLayout, topSlidingMenuFragment);
        fragmentTransaction.commit();

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


    @Override
    public void onFragmentInteraction() {
        Toast.makeText(this, "Hello TopSlidingMenu", Toast.LENGTH_SHORT).show();
    }
}