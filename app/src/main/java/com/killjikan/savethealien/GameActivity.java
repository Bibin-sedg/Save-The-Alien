package com.killjikan.savethealien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private ImageView alien,witch1,witch2,witch3,coin1,coin2,right1,right2,right3;
    private TextView textViewScore, textViewStart;
    private ConstraintLayout constraintLayout;
    private boolean touchControl = false;
    private boolean beginControl =false;
    private Runnable runnable,runnable2;
    private Handler handler,handler2;
    int ufoX,witch1x,witch2x,witch3x,coin2x,coin1x;
    int ufoY,witch1y,witch2y,witch3y,coin1y,coin2y;
    int screenWidth;
    int screenHeight;

    int right =3;

    int score =0;

    private MediaPlayer mediaPlayer;
    boolean status =false;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        alien = findViewById(R.id.iVAlien);
        witch1 = findViewById(R.id.ivWitch1);
        witch2 = findViewById(R.id.ivWitch2);
        witch3 = findViewById(R.id.ivWitch3);
        coin1 = findViewById(R.id.iVCoin1);
        coin2 = findViewById(R.id.iVCoin2);
        right1 = findViewById(R.id.right1);
        right2 = findViewById(R.id.right2);
        right3 = findViewById(R.id.right3);
        textViewScore = findViewById(R.id.tvScore);
        textViewStart = findViewById(R.id.tVStartInfo);
        constraintLayout = findViewById(R.id.constraintLayout);

        constraintLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                textViewStart.setVisibility(View.INVISIBLE);

                if(!beginControl)
                {
                    beginControl = true;

                    screenWidth = (int) constraintLayout.getWidth();
                    screenHeight = (int) constraintLayout.getHeight();

                    ufoX = (int) alien.getX();
                    ufoY = (int) alien.getY();

                    handler = new Handler();
                    runnable = new Runnable() {
                        @Override
                        public void run() {
                            moveToUFO();
                            witchControl();
                            collisionControl();

                        }
                    };
                    handler.post(runnable);
                }
                else
                {
                    if(motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                    {
                        touchControl =true;
                    }
                    if(motionEvent.getAction() == MotionEvent.ACTION_UP)
                    {
                        touchControl = false;
                    }
                }

                return true;
            }
        });

    }

    public void moveToUFO()
    {
        if(touchControl)
        {
            ufoY = ufoY - (screenHeight/50);
        }
        else
        {
            ufoY = ufoY + (screenHeight/50);
        }
        if(ufoY<=0)
        {
            ufoY=0;
        }
        if(ufoY>=(screenHeight - alien.getHeight()))
        {
            ufoY = (screenHeight-alien.getHeight());
        }
        alien.setY(ufoY);
    }
    public void witchControl()
    {
        witch1.setVisibility(View.VISIBLE);
        witch2.setVisibility(View.VISIBLE);
        witch3.setVisibility(View.VISIBLE);
        coin1.setVisibility(View.VISIBLE);
        coin2.setVisibility(View.VISIBLE);

        witch1x = witch1x - (screenWidth / 150);

        if(witch1x<0)
        {
            witch1x = screenWidth + 200;
            witch1y =(int) Math.floor(Math.random() * screenHeight);
            if(witch1y<=0)
            {
                witch1y=0;
            }
            if(witch1y>=(screenHeight - witch1.getHeight()))
            {
                witch1y = (screenHeight-witch1.getHeight());
            }
        }
        witch1.setX(witch1x);
        witch1.setY(witch1y);

        witch2x = witch2x - (screenWidth / 140);

        if(witch2x<0)
        {
            witch2x = screenWidth + 200;
            witch2x =(int) Math.floor(Math.random() * screenHeight);
            if(witch2y<=0)
            {
                witch2y=0;
            }
            if(witch2y>=(screenHeight - witch2.getHeight()))
            {
                witch2y = (screenHeight-witch2.getHeight());
            }
        }
        witch2.setX(witch2x);
        witch2.setY(witch2y);

        witch3x = witch3x - (screenWidth / 130);

        if(witch3x<0)
        {
            witch3x = screenWidth + 200;
            witch3y =(int) Math.floor(Math.random() * screenHeight);
            if(witch3y<=0)
            {
                witch3y=0;
            }
            if(witch3y>=(screenHeight - witch3.getHeight()))
            {
                witch3y = (screenHeight-witch3.getHeight());
            }
        }
        witch3.setX(witch3x);
        witch3.setY(witch3y);

        coin1x = coin1x - (screenWidth / 120);

        if(coin1x<0)
        {
            coin1x = screenWidth + 200;
            coin1y =(int) Math.floor(Math.random() * screenHeight);
            if(coin1y<=0)
            {
                coin1y=0;
            }
            if(coin1y>=(screenHeight - coin1.getHeight()))
            {
                coin1y = (screenHeight-coin1.getHeight());
            }
        }
        coin1.setX(coin1x);
        coin1.setY(coin1y);

        coin2x = coin2x - (screenWidth / 110);

        if(coin2x<0)
        {
            coin2x = screenWidth + 200;
            coin2y =(int) Math.floor(Math.random() * screenHeight);
            if(coin2y<=0)
            {
                coin2y=0;
            }
            if(coin2y>=(screenHeight - coin2.getHeight()))
            {
                coin2y = (screenHeight-coin2.getHeight());
            }
        }
        coin2.setX(coin2x);
        coin2.setY(coin2y);



    }
    public void collisionControl()
    {
        int centerWitch1x = witch1x + witch1.getWidth()/2;
        int centerWitch1y = witch1y + witch1.getHeight()/2;

        if(centerWitch1x >= ufoX
                && centerWitch1x <= (ufoX + alien.getWidth())
                && centerWitch1y >= ufoY
                && centerWitch1y <= (ufoY+ alien.getHeight()))
        {
            witch1x = screenWidth + 200;
            right--;
        }

        int centerWitch2x = witch2x + witch2.getWidth()/2;
        int centerWitch2y = witch2y + witch2.getHeight()/2;

        if(centerWitch2x >= ufoX
                && centerWitch2x <= (ufoX + alien.getWidth())
                && centerWitch2y >= ufoY
                && centerWitch2y <= (ufoY+ alien.getHeight()))
        {
            witch2x = screenWidth + 200;
            right--;
        }

        int centerWitch3x = witch3x + witch3.getWidth()/2;
        int centerWitch3y = witch3y + witch3.getHeight()/2;

        if(centerWitch3x >= ufoX
                && centerWitch3x <= (ufoX + alien.getWidth())
                && centerWitch3y >= ufoY
                && centerWitch3y <= (ufoY+ alien.getHeight()))
        {
            witch3x = screenWidth + 200;
            right--;
        }

        int centerCoin1x = coin1x + coin1.getWidth()/2;
        int centerCoin1y = coin1y + coin1.getHeight()/2;

        if(centerCoin1x >= ufoX
                && centerCoin1x <= (ufoX + alien.getWidth())
                && centerCoin1y >= ufoY
                && centerCoin1y <= (ufoY+ alien.getHeight()))
        {
            coin1x = screenWidth + 200;
            score = score+10;
            textViewScore.setText(""+score);
        }

        int centerCoin2x = coin2x + coin2.getWidth()/2;
        int centerCoin2y = coin2y + coin2.getHeight()/2;

        if(centerCoin2x >= ufoX
                && centerCoin2x <= (ufoX + alien.getWidth())
                && centerCoin2y >= ufoY
                && centerCoin2y <= (ufoY+ alien.getHeight()))
        {
            coin2x = screenWidth + 200;
            score = score+10;
            textViewScore.setText(""+score);
        }
        if(right>0 && score<200)
        {
            if(right ==2)
            {
                right1.setImageResource(R.drawable.baseline_favorite_grey);
            }
            if(right ==1)
            {
                right2.setImageResource(R.drawable.baseline_favorite_grey);
            }
            handler.postDelayed(runnable,20);
        }
        else if(score>=200)
        {
            handler.removeCallbacks(runnable);
            constraintLayout.setEnabled(false);
            textViewStart.setVisibility(View.VISIBLE);
            textViewStart.setText("Congratulations! You saved Dhaya. Now Dhaya own you once");
            witch1.setVisibility(View.INVISIBLE);
            witch2.setVisibility(View.INVISIBLE);
            witch3.setVisibility(View.INVISIBLE);
            coin1.setVisibility(View.INVISIBLE);
            coin2.setVisibility(View.INVISIBLE);

            handler2 = new Handler();
            runnable2 = new Runnable() {
                @Override
                public void run() {
                    ufoX = ufoX + (screenWidth/300);
                    alien.setX(ufoX);
                    alien.setY(screenHeight/2f);
                    if(ufoX<=screenWidth)
                    {
                        handler2.postDelayed(runnable2,20);
                    }
                    else {
                        handler2.removeCallbacks(runnable2);
                        Intent intent = new Intent(GameActivity.this,ResultActivity.class);
                        intent.putExtra("score",score);
                        startActivity(intent);
                        finish();
                    }
                }
            };
            handler2.post(runnable2);
        }
        else if(right ==0)
        {
            handler.removeCallbacks(runnable);
            right3.setImageResource(R.drawable.baseline_favorite_grey);
            Intent intent = new Intent(GameActivity.this,ResultActivity.class);
            intent.putExtra("score",score);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        mediaPlayer = MediaPlayer.create(GameActivity.this,R.raw.dark_room);
        mediaPlayer.start();
        status = getIntent().getBooleanExtra("status",false);
        if(status)
        {
            mediaPlayer.setVolume(0,0);
            status=true;
        }
        else
        {
            mediaPlayer.setVolume(1,1);
            status=false;
        }


    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.reset();

    }
}