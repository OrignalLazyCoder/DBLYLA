package com.downbeat.downbeat.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.downbeat.downbeat.Meditate.MeditateActivity;
import com.downbeat.downbeat.MoodManager.MoodMainActivity;
import com.downbeat.downbeat.R;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class HomeMainActivity extends AppCompatActivity {

    ImageButton downbeatIconImageButton, meditateIconImageButton, moodIconImageButton;
    SlidingUpPanelLayout slidingUpPanelLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);

        downbeatIconImageButton = findViewById(R.id.downbeatIconImageButton);
        meditateIconImageButton = findViewById(R.id.meditateIconImageButton);
        moodIconImageButton = findViewById(R.id.moodIconImageButton);

        slidingUpPanelLayout = findViewById(R.id.slidingTopMenuLayout);

        slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);

        downbeatIconImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MeditateActivity.class));
                finish();
            }
        });

        meditateIconImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MeditateActivity.class));
                finish();
            }
        });

        moodIconImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MoodMainActivity.class));
                finish();
            }
        });

    }


    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }


}