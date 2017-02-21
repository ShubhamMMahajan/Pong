package com.example.shubham.pong;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ScoreDisplay extends AppCompatActivity {
    TextView scoreOutput;

    String score1, score2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_display);
        scoreOutput = (TextView) findViewById(R.id.playerScores);
        //Log.d("Score Display", "This class has been called");
        Intent intent = getIntent();

        score1 = getIntent().getStringExtra("player1Score");
        score2 = getIntent().getStringExtra("player2Score");
        String player = getIntent().getStringExtra("scored");
        //Log.d("player", player);
        //Log.d("score 1", score1);
        //Log.d("score2", score2);

        if(player.equals("1")){

            int score = Integer.parseInt(score1);
            score += 1;
            score1 = score + "";
        }
        else if(player.equals("2")){
            //Log.d("test", "does this print");
            int score = Integer.parseInt(score2);
            score += 1;
            score2 = score + "";
        }
        //Log.d("input", "the input is " );
       // Log.d("score2", score2);
        scoreOutput.setText(score1 + " - " + score2);

    }



    public void playAgain(View view) {
        Intent intent = new Intent(ScoreDisplay.this, playGame.class);
        intent.putExtra("score1", score1);
        intent.putExtra("score2", score2);
        startActivity(intent);
    }
}
