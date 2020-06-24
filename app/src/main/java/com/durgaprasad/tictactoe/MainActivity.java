package com.durgaprasad.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;

import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String winner ;
    int moves=0;
    boolean isYellow = true;
    boolean isGameOver = false;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winingMoves = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    // red = 0 , yellow =1 , empty = 2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnClicked(View view){
        if(moves == 8){
            TextView textView = (TextView) findViewById(R.id.winnerTextView);
            Button button = (Button) findViewById(R.id.tryAgainButton);
            textView.setText("Tie game");
            textView.setVisibility(View.VISIBLE);
            button.setVisibility(view.VISIBLE);
        }
        moves++;
        ImageView counter = (ImageView) view;
        if(gameState[Integer.parseInt(counter.getTag().toString())]==2 && !isGameOver){
        counter.setTranslationY(-1500);
        if(isYellow) {
            counter.setImageResource(R.drawable.yellow);
            isYellow = false;
            gameState[Integer.parseInt(counter.getTag().toString())]= 1;
        }
        else {
            counter.setImageResource(R.drawable.red);
            isYellow = true;
            gameState[Integer.parseInt(counter.getTag().toString())]= 0;
        }
        counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

        for(int[] winningMove: winingMoves){
            if(gameState[winningMove[0]] == gameState[winningMove[1]] && gameState[winningMove[1]] == gameState[winningMove[2]] && gameState[winningMove[2]] != 2){
                if (isYellow){
                    winner = "Red";
                    isGameOver = true;
                    TextView textView = (TextView) findViewById(R.id.winnerTextView);
                    Button button = (Button) findViewById(R.id.tryAgainButton);
                    textView.setText(winner + " won the game");
                    textView.setVisibility(View.VISIBLE);
                    button.setVisibility(view.VISIBLE);
                }
                else{
                    isGameOver = true;
                    winner = "Yellow";
                    TextView textView = (TextView) findViewById(R.id.winnerTextView);
                    Button button = (Button) findViewById(R.id.tryAgainButton);
                    textView.setText(winner + " won the game");
                    textView.setVisibility(View.VISIBLE);
                    button.setVisibility(view.VISIBLE);
                }

            }
        }
        }
    }
    public void tryAgainBtnClicked(View view){

        TextView textView = (TextView) findViewById(R.id.winnerTextView);
        Button button = (Button) findViewById(R.id.tryAgainButton);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        textView.setVisibility(View.INVISIBLE);
        button.setVisibility(view.INVISIBLE);
        isYellow = true;
        isGameOver = false;
        moves = 0;
        for(int i=0;i<gridLayout.getChildCount();i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
            gameState[i] = 2;
        }
    }
}
