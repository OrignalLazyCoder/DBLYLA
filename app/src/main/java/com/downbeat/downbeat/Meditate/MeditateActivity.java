package com.downbeat.downbeat.Meditate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.downbeat.downbeat.Adapter.MeditateAdapter;
import com.downbeat.downbeat.Models.MeditateInformation;
import com.downbeat.downbeat.R;

import java.util.ArrayList;

public class MeditateActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<MeditateInformation> imageModelArrayList;
    private MeditateAdapter adapter;

    private int[] myImageList = new int[]{R.drawable.meditatebg, R.drawable.meditatebg,R.drawable.meditatebg, R.drawable.meditatebg,R.drawable.meditatebg,R.drawable.meditatebg,R.drawable.meditatebg};
    private String[] myImageNameList = new String[]{"1","2" ,"3","4","5","6","7"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditate);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        imageModelArrayList = addMeditate();
        adapter = new MeditateAdapter(this, imageModelArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

    }

    private ArrayList<MeditateInformation> addMeditate(){

        ArrayList<MeditateInformation> list = new ArrayList<>();

        for(int i = 0; i < 7; i++){
            MeditateInformation fruitModel = new MeditateInformation();
            fruitModel.setMeditateName(myImageNameList[i]);
            fruitModel.setUrl(myImageList[i]);
            list.add(fruitModel);
        }

        return list;
    }
}
