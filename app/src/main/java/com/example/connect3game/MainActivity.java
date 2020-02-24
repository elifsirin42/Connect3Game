package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0 = yellow , 1 = red
    int activePlayer = 0;

    // 2 means unplayed
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    //Winning Position.
    int[][] winningPositions = { {0, 1, 2},{3, 4, 5},{6, 7, 8},{0, 3, 6},{1, 4, 7},
            {2, 5, 8},{0, 4, 8},{2, 4, 6}};

    Boolean gameIsActive = true;


    public void dropIn(View view) {


        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && gameIsActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);

                //Playing order is yellow.
                activePlayer = 1;

            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;

            }
            counter.animate().translationYBy(1000f).rotationXBy(3600).setDuration(360);


            for(int[] winnigPosition : winningPositions){

                if(gameState[winnigPosition[0]] == gameState[winnigPosition[1]] &&
                        gameState[winnigPosition[1]] == gameState[winnigPosition[2]] &&
                        gameState[winnigPosition[0]] != 2){

                    //Someone has won!
                    gameIsActive = false;
                    String winner = "Red";
                    if(gameState[winnigPosition[0]] == 0){
                        winner = "Yellow";
                    }

                    LinearLayout winnerLayout = findViewById(R.id.winner_layout);
                    TextView winnerTxt = findViewById(R.id.textView3);

                    winnerTxt.setText(winner +" has won!");
                    winnerLayout.setVisibility(LinearLayout.VISIBLE);

                }else{
                    //if game is draw
                    boolean gameIsOver = true;
                    for (int counterState : gameState){
                         if (counterState == 2) gameIsOver = false;
                    }
                    if (gameIsOver){
                        LinearLayout winnerLayout = findViewById(R.id.winner_layout);
                        TextView winnerTxt = findViewById(R.id.textView3);

                        winnerTxt.setText("It is a draw!");
                        winnerLayout.setVisibility(LinearLayout.VISIBLE);
                    }


                }


            }



        }
    }
    public void playAgain(View view){

        gameIsActive = true;

        LinearLayout winnerLayout = findViewById(R.id.winner_layout);
        winnerLayout.setVisibility(View.INVISIBLE);

        for(int i = 0; i<gameState.length; i++ ){

            gameState[i] = 2;
        }

        GridLayout gameArea = findViewById(R.id.GameArea);
        for(int i = 0; i<gameArea.getChildCount(); i++){

            ((ImageView) gameArea.getChildAt(i)).setImageResource(0);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
