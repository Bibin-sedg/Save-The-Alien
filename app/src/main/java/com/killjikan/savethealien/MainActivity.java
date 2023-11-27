package com.killjikan.savethealien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView alien,witch1,witch2,witch3,coin,volume;
    private Button buttonStart;
    private Animation animation;
    private MediaPlayer mediaPlayer;
    boolean status = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alien = findViewById(R.id.mcAlien);
        witch1 = findViewById(R.id.witch1);
        witch2 = findViewById(R.id.witch2);
        witch3 = findViewById(R.id.witch3);
        coin = findViewById(R.id.coin);
        volume = findViewById(R.id.volume);
        buttonStart = findViewById(R.id.startBtn);

        animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.scale_animation);
        alien.setAnimation(animation);
        witch1.setAnimation(animation);
        witch2.setAnimation(animation);
        witch3.setAnimation(animation);
        coin.setAnimation(animation);

    }


    @Override
    protected void onResume() {
        super.onResume();

        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.horror_stories);
        mediaPlayer.start();

        volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!status)
                {
                    mediaPlayer.setVolume(0,0);
                    volume.setImageResource(R.drawable.baseline_volume_off_24);
                    status=true;
                }
                else
                {
                    mediaPlayer.setVolume(1,1);
                    volume.setImageResource(R.drawable.baseline_volume_up_24);
                    status=false;
                }
            }
        });

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mediaPlayer.reset();
                volume.setImageResource(R.drawable.baseline_volume_up_24);
                Intent intent = new Intent(MainActivity.this,GameActivity.class);
                intent.putExtra("status",status);
                startActivity(intent);
            }
        });


    }
    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.reset();

    }

}