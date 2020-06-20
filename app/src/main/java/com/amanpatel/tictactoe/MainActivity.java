package com.amanpatel.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive=true;
    /*
    Player Representations
    0 - X
    1 - O
    */
    int activePlayer=0;
    /*
    State Meanings
    0 - X
    1 - O
    2 - null
    */
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},
                              {0,3,6},{1,4,7},{2,5,8},
                              {0,4,8},{8,4,6}};
    public void playerTap(View view){
        ImageView img = (ImageView) view;
        int tappedImages=Integer.parseInt(img.getTag().toString());
        if(!gameActive)
        {
            gameReset(view);
        }
        if(gameState[tappedImages]==2)
        {
            gameState[tappedImages]=activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer==0)
            {
                img.setImageResource(R.drawable.cross);
                activePlayer=1;
                TextView status=findViewById(R.id.status);
                status.setText("O's Turn- Tap to play");
            }
            else
            {
                img.setImageResource(R.drawable.circle);
                activePlayer=0;
                TextView status=findViewById(R.id.status);
                status.setText("X's Turn- Tap to play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        // Check if any player has won
        for (int[] winningPositions: winningPositions)
        {
            if(gameState[winningPositions[0]]==gameState[winningPositions[1]]&&
                    gameState[winningPositions[1]]==gameState[winningPositions[2]]&&
                    gameState[winningPositions[0]]!=2)
            {
                //Somebody has won! - Find out who!
                String winnerStr;
                gameActive=false;
                if(gameState[winningPositions[0]]==0)
                {
                    winnerStr= "X has won!";
                }
                else
                {
                    winnerStr="O has won!";
                }
                //update the status bar for winner announcement
                TextView status=findViewById(R.id.status);
                status.setText(winnerStr);
            }

        }


    }
    public void gameReset(View view)
    {
        gameActive=true;
        activePlayer=0;
        for (int i=0;i<gameState.length;i++)
        {
            gameState[i]=2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        TextView status=findViewById(R.id.status);
        status.setText("X's Turn- Tap to play");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}