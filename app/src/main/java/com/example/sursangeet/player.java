package com.example.sursangeet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;

public class player extends AppCompatActivity {
    ImageButton rew, start, fwd, stop;
    SeekBar sb;
    ImageView iv;
    TextView tv;
    MediaPlayer player;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        tv = findViewById(R.id.tv);
        iv = findViewById(R.id.iv);
        sb = findViewById(R.id.sb);
        rew = findViewById(R.id.rwd);
        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);
        fwd = findViewById(R.id.fwd);

        Intent intent = getIntent();
        String name = intent.getStringExtra("music");
        int path = intent.getIntExtra("path", 0);
        int imgsrc = intent.getIntExtra("img", 0);

        tv.setText(name);

        player = MediaPlayer.create(this, path);
        iv.setImageResource(imgsrc);

        sb.setMax(player.getDuration()); // Set max progress of SeekBar to the duration of the audio

        // SeekBar change listener
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser)
                    player.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        updateSeekBar();
    }

    private void updateSeekBar() {
        if (player.isPlaying()) { // Check if MediaPlayer is playing
            sb.setProgress(player.getCurrentPosition());
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateSeekBar();
            }
        }, 1000);
    }

    public void rewind(View view) {
        player.seekTo(player.getCurrentPosition() - (player.getDuration() / 10));
    }

    public void start(View view) {
        if (!player.isPlaying()) {
            player.start();
            start.setEnabled(false);
            stop.setEnabled(true);
        }
    }

    public void forward(View view) {
        player.seekTo(player.getCurrentPosition() + (player.getDuration() / 10));
    }

    public void stop(View view) {
        if (player.isPlaying()) {
            player.pause(); // Pause instead of stop to allow resuming from the same position
            start.setEnabled(true);
            stop.setEnabled(false);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.release(); // Release MediaPlayer when activity is destroyed
        }
    }

    public void back(View view) {
        Intent i =new Intent(getApplicationContext(), MusicList.class);
        startActivity(i);
    }
}
