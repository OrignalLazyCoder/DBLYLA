package com.downbeat.downbeat.MoodManager;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.downbeat.downbeat.Adapter.BlogAdapter;
import com.downbeat.downbeat.Models.BlogInformation;
import com.downbeat.downbeat.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MoodMainActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("users");

    Button awesomeMoodButton;
    Button happyMoodButton;
    Button sadMoodButton;
    Button devastatedMoodButton;
    LineChart mChart;
    RecyclerView recyclerView;

    int graphXAxisCounter = 1;
    int lastMoodValue = 0;
    int currentMoodValue = 0;

    int[] imageList = new int[]{R.drawable.meditateicon , R.drawable.appicon , R.drawable.relaxicon , R.drawable.chaticon};
    String[] blogTitle = new String[]{"1" ,"2","3","4"};
    private  ArrayList<BlogInformation> blogInformations;
    private BlogAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_main);

        awesomeMoodButton = findViewById(R.id.awesomeMoodButton);
        happyMoodButton = findViewById(R.id.happyMoodButton);
        sadMoodButton = findViewById(R.id.sadMoodButton);
        devastatedMoodButton = findViewById(R.id.devastatedMoodButton);
        recyclerView = findViewById(R.id.blogRecyclerView);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        mChart = findViewById(R.id.chart);
        mChart.setTouchEnabled(true);
        mChart.setPinchZoom(true);

        //To make temo blog view
        blogInformations = makeList();
        adapter = new BlogAdapter(this , blogInformations);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));


        //Make graph Visible
//        final GraphView graph = (GraphView) findViewById(R.id.graph);
//        graph.setVisibility(View.VISIBLE);

        databaseReference.child(firebaseUser.getUid()).child("Mood").child("init").setValue("0");

        //Getting data from firebase and plotting it on graph
        databaseReference.child(firebaseUser.getUid()).child("Mood").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mChart.clear();
                ArrayList<Entry> values = new ArrayList<>();
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    String dateTime  = dsp.getKey();
                    String mood   = dsp.getValue().toString();
//                    Toast.makeText(getApplicationContext() , dateTime + " "+ mood , Toast.LENGTH_SHORT).show();
                    switch(mood){
                        case "Awesome":currentMoodValue = 4;
                        break;
                        case "Happy":currentMoodValue = 3;
                        break;
                        case "Sad":currentMoodValue = 2;
                        break;
                        case "Devastated":currentMoodValue = 1;
                        break;
                        case "0":currentMoodValue = 0;
                        break;
                    }

                    if(currentMoodValue!=0) {
                        values.add(new Entry(graphXAxisCounter, currentMoodValue));
                        graphXAxisCounter++;
                    }
                }
                LineDataSet set1;
                if (mChart.getData() != null &&
                        mChart.getData().getDataSetCount() > 0) {
                    set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
                    set1.setValues(values);
                    mChart.getData().notifyDataChanged();
                    mChart.notifyDataSetChanged();
                } else {
                    set1 = new LineDataSet(values, "Mood Values");
                    set1.setDrawIcons(false);
                    set1.enableDashedLine(10f, 5f, 0f);
                    set1.enableDashedHighlightLine(10f, 5f, 0f);
                    set1.setColor(Color.DKGRAY);
                    set1.setCircleColor(Color.DKGRAY);
                    set1.setLineWidth(1f);
                    set1.setCircleRadius(3f);
                    set1.setDrawCircleHole(false);
                    set1.setValueTextSize(9f);
                    set1.setDrawFilled(true);
                    set1.setFormLineWidth(1f);
                    set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
                    set1.setFormSize(15.f);
                    ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                    dataSets.add(set1);
                    LineData data = new LineData(dataSets);
                    mChart.setData(data);
                }
            }


        @Override
        public void onCancelled(DatabaseError error) {

        }
    });

        awesomeMoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String ID = sdf3.format(c.getTime());
                databaseReference.child(firebaseUser.getUid()).child("Mood").child(ID).setValue("Awesome");
            }
        });

        happyMoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String ID = sdf3.format(c.getTime());
                databaseReference.child(firebaseUser.getUid()).child("Mood").child(ID).setValue("Happy");
            }
        });

        sadMoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String ID = sdf3.format(c.getTime());
                databaseReference.child(firebaseUser.getUid()).child("Mood").child(ID).setValue("Sad");
            }
        });

        devastatedMoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String ID = sdf3.format(c.getTime());
                databaseReference.child(firebaseUser.getUid()).child("Mood").child(ID).setValue("Devastated");
            }
        });

    }

    private ArrayList<BlogInformation> makeList() {

        ArrayList<BlogInformation> blogInformation = new ArrayList<>();
        
        for(int i=0 ; i < 4 ; i++){
            BlogInformation j = new BlogInformation();
            j.setImageUrl(imageList[i]);
            j.setBlogTitle(blogTitle[i]);
            blogInformation.add(j);
        }
        return blogInformation;
    }
}
