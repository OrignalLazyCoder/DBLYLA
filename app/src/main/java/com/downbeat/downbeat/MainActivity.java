package com.downbeat.downbeat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.downbeat.downbeat.Home.HomeMainActivity;
import com.downbeat.downbeat.Login.Login;
import com.downbeat.downbeat.Meditate.MeditateActivity;

public class MainActivity extends AppCompatActivity {

    ImageButton nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //startActivity(new Intent(this, MeditateActivity.class));
         nextBtn = findViewById(R.id.nextButton);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , Login.class));
            }
        });

    }
}
