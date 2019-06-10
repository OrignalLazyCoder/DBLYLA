package com.downbeat.downbeat.Services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class MyAudioPlayService extends Service implements MediaPlayer.OnCompletionListener, MediaPlayer.OnSeekCompleteListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener, MediaPlayer.OnBufferingUpdateListener {

    private final IBinder mBinder = new MyAudioPlayServiceBinder();

    private MediaPlayer mediaPlayer;
    private String audioLink;


    public MyAudioPlayService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mediaPlayer = new MediaPlayer();

        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnSeekCompleteListener(this);
        mediaPlayer.setOnErrorListener(this);
        mediaPlayer.setOnInfoListener(this);
        mediaPlayer.setOnBufferingUpdateListener(this);


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        audioLink = intent.getStringExtra("audioLink");
        mediaPlayer.reset();

        if (!mediaPlayer.isPlaying()) {

            try {
                mediaPlayer.setDataSource(audioLink);
                mediaPlayer.prepareAsync();

            } catch (Exception e) {
                Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mediaPlayer != null) {

            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }

            mediaPlayer.release();
        }
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

    }

    @Override
    public void onCompletion(MediaPlayer mp) {

        if (mp.isPlaying()) {
            mp.stop();
        }

        stopSelf();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {

        switch (what) {

            case MediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK: {
                Toast.makeText(this, "MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK", Toast.LENGTH_SHORT).show();
                break;
            }

            case MediaPlayer.MEDIA_ERROR_SERVER_DIED: {
                Toast.makeText(this, "MEDIA_ERROR_SERVER_DIED:", Toast.LENGTH_SHORT).show();
                break;
            }

            case MediaPlayer.MEDIA_ERROR_UNKNOWN: {
                Toast.makeText(this, "MEDIA_ERROR_UNKNOWN", Toast.LENGTH_SHORT).show();
                break;
            }
        }
        return false;
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {

        if (!mp.isPlaying()) {
            mp.start();
        }

    }

    @Override
    public void onSeekComplete(MediaPlayer mp) {

    }

    public class MyAudioPlayServiceBinder extends Binder {
        public MyAudioPlayServiceBinder getService() {
            return MyAudioPlayServiceBinder.this;
        }

        public void pauseMusic() {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
        }

        public void playMusic() {
            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
            }
        }


    }


}
