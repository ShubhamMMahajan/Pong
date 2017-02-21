package com.example.shubham.pong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);
        Button play = (Button) findViewById(R.id.playButton);


    }

    public void PlayTheGame(View v){
        Log.d("clicked", "The button was clicked");
        Intent i = new Intent(MainActivity.this, playGame.class);
        startActivity(i);
    }


}
