package com.killjikan.savethealien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView tvResult, tvScore, tvHighScore;
    private Button btnPlayAgain;
    private int score;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvResult = findViewById(R.id.tvResultInfo);
        tvScore = findViewById(R.id.tvMyScore);
        tvHighScore = findViewById(R.id.tvHighScore);
        btnPlayAgain  = findViewById(R.id.btnPlayAgain);

        score = getIntent().getIntExtra("score",0);
        tvScore.setText("Your Score: "+score);

        sharedPreferences = this.getSharedPreferences("Score", Context.MODE_PRIVATE);
        int highScore = sharedPreferences.getInt("highestScore",0);

        if(score>=200)
        {
            tvHighScore.setText("Highest Score: "+score);
            sharedPreferences.edit().putInt("highestScore",score).apply();

        }
        else if (score >= highScore)
        {
            tvResult.setText("Sorry! You lost the game.");
            tvHighScore.setText("Highest Score: "+score);
            sharedPreferences.edit().putInt("highestScore",score).apply();
        }
        else {
            tvResult.setText("Sorry! You lost the game.");
            tvHighScore.setText("Highest Score: "+highScore);
        }
        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(ResultActivity.this);
        builder.setTitle("Help The Paithiyakar Alien");
        builder.setMessage("Are you sure you want to quit the game?");
        builder.setCancelable(false);
        builder.setNegativeButton("Quit game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                moveTaskToBack(true);
                Process.killProcess(Process.myPid());
                System.exit(0);
            }
        });
        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.create().show();
    }
}