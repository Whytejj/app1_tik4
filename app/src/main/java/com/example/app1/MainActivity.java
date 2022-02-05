package com.example.app1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.support.v7.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int state = 0;
    int[] gameBoard = {2,2,2,2,2,2,2,2,2};
    int[][] wins = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean active = true;

    public void dropDown(View view){
        ImageView counter = (ImageView) view;
        int tapedCounter = Integer.parseInt(counter.getTag().toString());
        TextView textView = (TextView) findViewById(R.id.textView);
        EditText p1Name = (EditText) findViewById(R.id.p1Name);
        EditText p2Name = (EditText) findViewById(R.id.p2Name);
        if(gameBoard[tapedCounter] == 2 && active == true) {
            if (state == 0) {
                textView.setText(p2Name.getText() + " turn");
                counter.setTranslationY(-1000f);
                counter.setImageResource(R.drawable.red);
                counter.animate().translationYBy(1000f).rotation(360 * 5).setDuration(400);
                state = 1;
                gameBoard[tapedCounter] = state;
            } else {
                textView.setText(p1Name.getText() + " turn");
                counter.setTranslationY(-1000f);
                counter.setImageResource(R.drawable.yellow);
                counter.animate().translationYBy(1000f).rotation(360 * 5).setDuration(400);
                state = 0;
                gameBoard[tapedCounter] = state;
            }
        }
        for(int[] posibilities : wins){
            LinearLayout message = (LinearLayout) findViewById(R.id.message);
            if(gameBoard[posibilities[0]] == 0 && gameBoard[posibilities[1]] == 0 && gameBoard[posibilities[2]] == 0){
                textView.setText("congratulation " + p2Name.getText());
                message.setVisibility(View.VISIBLE);
                active = false;
            }
            else if(gameBoard[posibilities[0]] == 1 && gameBoard[posibilities[1]] == 1 && gameBoard[posibilities[2]] == 1) {
                textView.setText("congratulation " + p1Name.getText());
                message.setVisibility(View.VISIBLE);
                active = false;
            }
        }
    }

    public void newGame(View view){
        LinearLayout message = (LinearLayout) findViewById(R.id.message);
        TextView textView = (TextView) findViewById(R.id.textView);
        message.setVisibility(View.INVISIBLE);
        state = 0;
        textView.setText("Lets Begin!");
        for(int i = 0; i < gameBoard.length; i++){
            gameBoard[i] = 2;
        }
        GridLayout grid = findViewById(R.id.grid);
        for(int i = 0; i < grid.getChildCount(); i++){
            ImageView image = (ImageView) grid.getChildAt(i);
            image.setImageResource(0);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
