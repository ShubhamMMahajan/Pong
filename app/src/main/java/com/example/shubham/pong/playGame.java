package com.example.shubham.pong;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.Dimension;
import android.text.Layout;
import android.util.Log;
import android.view.Display;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Toast;

import java.util.Random;
import android.os.Handler;
import java.util.logging.LogRecord;

import javax.net.ssl.SSLSession;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import static android.R.attr.action;
import static android.R.attr.content;
import static android.R.attr.gravity;
import static android.R.attr.handle;
import static android.R.attr.left;
import static android.R.attr.max;
import static android.R.attr.right;
import static android.R.attr.start;
import static android.R.attr.x;
import static android.R.attr.y;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static android.util.Log.w;
import static android.view.View.X;
import static com.example.shubham.pong.R.drawable.ball;
import static com.example.shubham.pong.R.id.field;
import static com.example.shubham.pong.R.id.gameField;
import static com.example.shubham.pong.R.id.leftHalf;
import static com.example.shubham.pong.R.id.player1Paddle;
import static com.example.shubham.pong.R.id.player1TouchScreen;
import static com.example.shubham.pong.R.id.player2TouchScreen;
import static com.example.shubham.pong.R.id.playerScores;
import static com.example.shubham.pong.R.id.rightHalf;

import android.view.View.OnClickListener;

import org.w3c.dom.Text;

public class playGame extends Activity implements OnTouchListener, Runnable{


    float maxX = 0;
    float maxY = 0;

    float minX = 90;
    float minY = 70;


    float dX;
    float dY;
    int width;
    int height;
    TextView paddle1, paddle2;
    RelativeLayout layout;
    RelativeLayout left, right;
    // Ball's properties
    int counter = 0;
    private double xPos = 100;
    private double yPos = 100;
    Random r = new Random();
    ImageView powerup;
    int degree = r.nextInt(50);
    private double yVel = Math.sin(Math.toRadians(degree)) * 4;
    private double xVel = Math.cos(Math.toRadians(degree)) * 4;
    private int count = 0;
    float paddle1Y = 0;
    float paddle2Y = 0;
    ImageView ball;
    //for high score
    int paddleWidth = 0;
    int paddleHeight = 0;
    boolean powerupOnBoard;


    int xPositionOfPowerUp = 0;
    int yPositionOfPowerUp = 0;

    int scores[] = new int[2];
    boolean stop;




    //
    LinearLayout window;
    private int windowHeight;
    private int windowWidth;
    Handler handler = new Handler();
    TextView paddle;
    RelativeLayout gameField;


    //RelativeLayout left = (RelativeLayout) findViewById(R.id.leftHalf);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Display display = getWindowManager().getDefaultDisplay();

        requestWindowFeature(Window.FEATURE_NO_TITLE);


        setContentView(R.layout.activity_play_game);

        ball = (ImageView) findViewById(R.id.ball);
        width = display.getWidth();
        height = display.getHeight();

            /*ball.clearAnimation();
            TranslateAnimation transAnim = new TranslateAnimation(5, 150, 5,
                    150);
            transAnim.setStartOffset(500);
            transAnim.setDuration(3000);
            transAnim.setFillAfter(true);
            transAnim.setInterpolator(new BounceInterpolator());
            transAnim.setAnimationListener(new AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {
                    Log.i("Start", "Starting button dropdown animation");

                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    Log.i("END",
                            "Ending button dropdown animation. Clearing animation and setting layout");
                    ball.clearAnimation();
                    final int left = ball.getLeft();
                    final int top = ball.getTop();
                    final int right = ball.getRight();
                    final int bottom = ball.getBottom();
                    ball.layout(left, top, right, bottom);

                }
            });
            ball.startAnimation(transAnim);*/


        //soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        //sample1 = soundPool.load(this, R.raw.squash1, 1);
        //sample2 = soundPool.load(this, R.raw.squash2, 1);




        findViewById(R.id.player1TouchScreen).setOnTouchListener(this);
        findViewById(R.id.player2TouchScreen).setOnTouchListener(this);
        //findViewById(R.id.field).setOnTouchListener(this);
        findViewById(R.id.leftHalf).setOnTouchListener(this);
        findViewById(R.id.rightHalf).setOnTouchListener(this);
        //ballMovement(findViewById(R.id.ball));
        paddle1 = (TextView) findViewById(player1Paddle);
        paddle2 = (TextView) findViewById(R.id.player2Paddle);
        Log.d("width on start", paddle2.getWidth() + "");


        window = (LinearLayout) findViewById(field);

        gameField = (RelativeLayout) findViewById(R.id.gameField);

        //RelativeLayout rlayout = (RelativeLayout) findViewById(R.id.field);
        //rlayout.setOnClickListener(this);

        layout = (RelativeLayout) findViewById(R.id.leftHalf);
        ViewTreeObserver vto = layout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    layout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                width = layout.getMeasuredWidth();
                height = layout.getMeasuredHeight();

            }
        });

        stop = false;

        window = (LinearLayout) findViewById(field);
        ViewTreeObserver vto2 = window.getViewTreeObserver();
        vto2.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    window.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    window.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                windowWidth = window.getMeasuredWidth();
                Log.d("Window Width", "" + windowWidth);
                maxX = windowWidth - 110;
                windowHeight = window.getMeasuredHeight();
                maxY = windowHeight - 70;

            }
        });



        paddle = (TextView) findViewById(player1Paddle);
        ViewTreeObserver vto3 = paddle.getViewTreeObserver();
        vto2.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    paddle.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    paddle.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                paddleWidth = paddle.getMeasuredWidth();
                paddleHeight = paddle.getMeasuredHeight();

            }
        });
        try {

            scores[0] = Integer.parseInt(getIntent().getStringExtra("score1"));
            scores[1] = Integer.parseInt(getIntent().getStringExtra("score2"));
        }
        catch (Exception e){

        }
        powerupOnBoard = false;


        /*window.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                float x = event.getX();
                float y = event.getY();
                event.getMetaState();
                Log.v("ON_TOUCH", "Action = " + action + " View:" + v.toString());
                Log.v("ON_TOUCH", "X = " + x + "Y = " + y);


                return false;
            }
        });*/








    }



    /*private MotionEvent simulateDoubleTapEvent(LinearLayout window, int action)
    {
        long downTime = SystemClock.uptimeMillis();
        long eventTime = SystemClock.uptimeMillis() + 100;
        float x = 35.407104f;
        float y = 378.52066f;
        // List of meta states found here: developer.android.com/reference/android/view/KeyEvent.html#getMetaState()
        int metaState = 0;
        MotionEvent me = MotionEvent.obtain(
                downTime,
                eventTime,
                action,
                x,
                y,
                metaState
        );
        window.dispatchTouchEvent(me);
        return me;
    }*/







    public void ballPlace() {

        if (xPos > xPositionOfPowerUp && xPos < xPositionOfPowerUp + 30 && yPos > yPositionOfPowerUp && yPos < yPositionOfPowerUp + 30) {
            ViewGroup parent = (ViewGroup) powerup.getParent();
            parent.removeView(powerup);
            powerupOnBoard = false;
            xVel = xVel * 2;
            yVel = yVel * 2;
        }

        else if (xPos < 90 && (yPos > paddle1Y  && (yPos < paddle1Y + paddleHeight + 50))) {
            xVel *= -1;
            Log.d("Position of Ball", "Xpos  " + xPos + "  yPos  " + yPos + "  paddle1Y   " + paddle1Y + "  paddle1 lower   " + (paddle1Y + paddleHeight));

            Log.d("Position of Ball", "Xpos  " + xPos + "  yPos  " + yPos + "  paddle2Y   " + paddle2Y + "  paddle1 lower   " + (paddle2Y + paddleHeight));


        }
        else if(xPos > 1810  && (yPos > paddle2Y && (yPos < paddle2Y + paddleHeight + 50))){
            xVel *= -1;
        }
        else if (yPos > windowHeight - 70) {
            yVel *= -1;
            Log.d("Y Postion of ball", "" + yPos);
        }


        else if (xPos < 90) {
            Intent intent = new Intent(playGame.this, ScoreDisplay.class);
            stop = true;

            String score1 = scores[0] + "";
            String score2 = scores[1] + "";
            String text = "" + score1 + " - " + score2;
            intent.putExtra("player1Score", score1);
            intent.putExtra("player2Score", score2);
            intent.putExtra("scored", "2");
            startActivity(intent);
        }
        else if(xPos > 1810) {
           // Log.d("Position of Ball", "Xpos  " + xPos + "  yPos  " + yPos + "  paddle1Y   " + paddle1Y + "  paddle1 lower   " + (paddle1Y + paddleHeight));
            //Log.d("Position of Ball", "Xpos  " + xPos + "  yPos  " + yPos + "  paddle2Y   " + paddle2Y + "  paddle2 lower   " + (paddle2Y + paddleHeight));

            Intent intent = new Intent(playGame.this, ScoreDisplay.class);
            stop = true;

            String score1 = scores[0] + "";
            String score2 = scores[1] + "";
            String text = score1 + " - " + score2;
            intent.putExtra("player1Score", score1);
            intent.putExtra("player2Score", score2);
            intent.putExtra("scored", "1");

            startActivity(intent);


        }
        else if (yPos < 60) {
            yVel *= -1;
        }
        xPos = xPos + (xVel);
        yPos = yPos + (yVel);

        ball.setX((int) xPos);
        ball.setY((int) yPos);






    }










   /* public boolean onDrag(View v, DragEvent event) {
        if (event.getAction() == DragEvent.ACTION_DROP) {
            //we want to make sure it is dropped only to left and right parent view
            View view = (View) event.getLocalState();

            if (v.getId() == R.id.player1TouchScreen) {

                ViewGroup source = (ViewGroup) view.getParent();
                source.removeView(view);

                LinearLayout target = (LinearLayout) v;
                target.addView(view);
            }
            //make view visible as we set visibility to invisible while starting drag
            view.setVisibility(View.VISIBLE);
        }
        return true;
    }*/


    public void ballMovement(View view) {

        Log.d("ball", "The ball function");

    }


    @Override
    public boolean onTouch(View view, MotionEvent event) {

        if(count == 0) {
            (new Thread(new Runnable() {

                @Override
                public void run() {
                    while (!Thread.interrupted())
                        try {
                            Thread.sleep(5);
                            Random rand = new Random();
                            final int powerup = rand.nextInt(2) + 10;
                            //Log.d("number", powerup + "");
                            //Log.d("powerupOnBoard", powerupOnBoard + "");

                            runOnUiThread(new Runnable() // start actions in UI thread
                            {

                                @Override
                                public void run() {
                                    if(powerup % 10 == 0 && !powerupOnBoard){
                                        generatePowerUp();
                                    }
                                    ballPlace(); // this action have to be in UI thread
                                    xVel = 1.0001 * xVel;
                                    yVel = 1.0001 * yVel;

                                    count++;
                                    if (count % 100 == 0) {
                                        //Log.d("speed", xPos + "");
                                    }


                                }
                            });
                            if(stop){
                                Log.d("Stop", "This program has stopped");
                                //Intent intent = new Intent(getApplicationContext(), playGame.class);
                                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                                //String score1 = scores[0] + "";
                                //String score2 = scores[1] + "";
                                //String text = score1 + " - " + score2;
                                //intent.putExtra("playersScore", text);
                                break;
                            }
                        } catch (InterruptedException e) {
                            // ooops
                        }

                }
            })).start(); // the while thread will start in BG thread
        }


        if(view.equals(findViewById(R.id.player1TouchScreen))){
            //Log.d("left half", "touched");
            view = (TextView) findViewById(player1Paddle);
        }

        else if (view.equals(findViewById(player2TouchScreen))){
            view = (TextView) findViewById(R.id.player2Paddle);
        }



        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:

                dX = view.getX() - event.getRawX();
                dY = view.getY() - event.getRawY();
                if(view.equals(findViewById(R.id.field))) {
                    //view.setPressed(true);
                }
                break;

            case MotionEvent.ACTION_MOVE:
                /*if (event.getRawX() + dX > width){
                    view.animate()
                            .x(width)
                            .y(event.getRawY() + dY)
                            .setDuration(0)
                            .start();
                    Log.d("Moved", "The paddle moved!");

                }*/

                if (event.getRawY() + dY > 825 && view.equals(paddle1)) {
                    view.animate()
                            .x(0)
                            .y(height - 120)
                            .setDuration(0)
                            .start();
                    paddle1Y = event.getRawY() + dY;



                } else if (event.getRawY() + dY < 0 && view.equals(paddle1)) {
                    view.animate()
                            .x(0)
                            .y(0)
                            .setDuration(0)
                            .start();
                    paddle1Y = event.getRawY() + dY;

                } else if (event.getRawY() + dY < 0 && view.equals(paddle2)) {
                    view.animate()
                            .x(width - 30)
                            .y(0)
                            .setDuration(0)
                            .start();
                    paddle2Y = event.getRawY() + dY;
                    Log.d("width", paddle2.getWidth() + "");
                    paddle2.setWidth(0);

                } else if (event.getRawY() + dY > 825 && view.equals(paddle2)) {
                    view.animate()
                            .x(width - 30)
                            .y(height - 120)
                            .setDuration(0)
                            .start();
                    paddle2Y = event.getRawY() + dY;
                    paddle2.setWidth(0);

                } else if (view.equals(paddle1)) {
                    view.animate()
                            .x(0)
                            .y(event.getRawY() + dY)
                            .setDuration(0)
                            .start();
                    paddle1Y = event.getRawY() + dY;

                } else if (view.equals(paddle2)) {
                    view.animate()
                            .x(width - 30)
                            .y(event.getRawY() + dY)
                            .setDuration(0)
                            .setDuration(0)
                            .start();
                    paddle2Y = event.getRawY() + dY;
                    paddle2.setWidth(0);

                    //Log.d("Width", width + "");

                }


                break;
            default:
                return false;
        }
        return true;

    }

    public void generatePowerUp(){
        powerupOnBoard = true;
        Log.d("powerup", "generate power up function");
        powerup = new ImageView(getApplicationContext());
        powerup.setImageResource(R.drawable.speedup);
        Random randomPosition = new Random();
        int xPosition = randomPosition.nextInt(1300-500 + 1) + 500;
        int yPosition = randomPosition.nextInt((int)maxY - 70 + 1) + (int)minY;

        Log.d("Window Height: ", "" + windowHeight);

        xPositionOfPowerUp = xPosition;
        yPositionOfPowerUp = yPosition;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(50, 50);
        //layoutParams.setMargins(xPosition, yPosition, 0, 0);
        //LinearLayout.LayoutParams params = window.generateLayoutParams();


        gameField.addView(powerup, layoutParams);
        //powerup.setX(xPosition);
        powerup.setX(xPosition);
        powerup.setY(yPosition);


        //powerup.setX(500);
        //powerup.setY(500);
        Log.d("X Position", "" + powerup.getX());
        Log.d("Y Position", "" + powerup.getY());
    }


    @Override
    public void run() {

    }
}






