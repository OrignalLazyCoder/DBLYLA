package com.downbeat.downbeat.Home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.downbeat.downbeat.R;

public class HomeMainActivity extends AppCompatActivity {

    boolean opened;
    LinearLayout viewParent;
    ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);

        viewParent = findViewById(R.id.viewParent);
        button = findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swipeMenu();
            }
        });

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
