package com.downbeat.downbeat.Meditate;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.downbeat.downbeat.Adapter.MeditateAdapter;
import com.downbeat.downbeat.Models.MeditateInformation;
import com.downbeat.downbeat.R;
import com.downbeat.downbeat.Services.MyAudioPlayService;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class MeditateActivity extends AppCompatActivity implements MeditateAdapter.MeditateAdapterInterface {

    boolean musicPlaying = false, isPause = false, isAudioConnectionBound = false;
    Intent audioServiceIntent;
    ImageButton btnPlayAudio, btnStopAudio;
    TextView tvAudioName;
    int currentAudioIndex = 0;
    private RecyclerView recyclerView;
    private ArrayList<MeditateInformation> meditateInformations;
    private MeditateAdapter adapter;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private MyAudioPlayService.MyAudioPlayServiceBinder myAudioPlayServiceBinder;
    private ServiceConnection mConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditate);

        btnPlayAudio = findViewById(R.id.btnPlayAudio);
        btnStopAudio = findViewById(R.id.btnStopAudio);

        tvAudioName = findViewById(R.id.tvAudioName);

        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        meditateInformations = new ArrayList<>();
        populateMeditateInformationList();


        recyclerView = findViewById(R.id.recycler);

        adapter = new MeditateAdapter(this, meditateInformations);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

        audioServiceIntent = new Intent(this, MyAudioPlayService.class);
        myAudioPlayServiceConnectionCreate();


        btnPlayAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!musicPlaying) {

                    playMusic(currentAudioIndex);

                    musicPlaying = true;

                } else {

                    pauseMusic(currentAudioIndex);

                }
            }
        });

        btnStopAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (musicPlaying) {

                    stopMusic(currentAudioIndex);

                }
            }
        });


    }

    private void populateMeditateInformationList() {

        databaseReference = firebaseDatabase.getReference();

        databaseReference.child("audios/meditation-audios").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    MeditateInformation md = data.getValue(MeditateInformation.class);
                    meditateInformations.add(md);
                    adapter.notifyDataSetChanged();
                    Log.d("AudioData", "AudioData: " + md);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void myAudioPlayServiceConnectionCreate() {
        mConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                myAudioPlayServiceBinder = ((MyAudioPlayService.MyAudioPlayServiceBinder) service).getService();
                Toast.makeText(MeditateActivity.this, "Service Connected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                myAudioPlayServiceBinder = null;
                Toast.makeText(MeditateActivity.this, "Service Disconnected", Toast.LENGTH_SHORT).show();
            }
        };
    }


    private void playMusic(int currentAudioIndex) {

        this.currentAudioIndex = currentAudioIndex;
        Toast.makeText(MeditateActivity.this, "playMusic()", Toast.LENGTH_SHORT).show();
        btnPlayAudio.setImageResource(R.drawable.btn_pause_audio);
        btnStopAudio.setClickable(true);

        if (!isAudioConnectionBound) {
            bindService(audioServiceIntent, mConnection, BIND_AUTO_CREATE);
            isAudioConnectionBound = true;
        }
        tvAudioName.setText(meditateInformations.get(currentAudioIndex).getName());
        if (isPause) {
            myAudioPlayServiceBinder.playMusic();
        } else {
            storageReference.child(meditateInformations.get(this.currentAudioIndex).getFullPath()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    audioServiceIntent.putExtra("audioLink", uri.toString());
                    startService(audioServiceIntent);
                    bindService(audioServiceIntent, mConnection, BIND_AUTO_CREATE);
                    isAudioConnectionBound = true;
                }
            });

        }


    }

    private void pauseMusic(int currentAudioIndex) {
        Toast.makeText(MeditateActivity.this, "pauseMusic()", Toast.LENGTH_SHORT).show();
        btnPlayAudio.setImageResource(R.drawable.playbutton);

        musicPlaying = false;
        isPause = true;
        if (!isAudioConnectionBound) {
            bindService(audioServiceIntent, mConnection, BIND_AUTO_CREATE);
            isAudioConnectionBound = true;
        }

        myAudioPlayServiceBinder.pauseMusic();
    }

    private void stopMusic(int currentAudioIndex) {

        Toast.makeText(MeditateActivity.this, "stopMusic()", Toast.LENGTH_SHORT).show();
        btnStopAudio.setClickable(false);
        btnPlayAudio.setImageResource(R.drawable.playbutton);
        musicPlaying = false;
        if (isAudioConnectionBound)
            unbindService(mConnection);
        stopService(audioServiceIntent);
    }


    @Override
    public void onAudioSelect(int index) {

        playMusic(index);
        Toast.makeText(this, meditateInformations.get(index).getName(), Toast.LENGTH_SHORT).show();
    }
}
