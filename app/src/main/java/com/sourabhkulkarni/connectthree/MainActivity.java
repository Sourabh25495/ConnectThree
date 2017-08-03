package com.sourabhkulkarni.connectthree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    int active_player=0;//0=yellow and 1=red
    //2= unplayed
    int[] gameState={2,2,2,2,2,2,2,2,2};
    boolean gameIsActive= true;

    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8}, {0,3,6}, {1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropIn(View view) {

        ImageView counter = (ImageView) view;



        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2&& gameIsActive) {

            counter.setTranslationY(-1000f);
            gameState[tappedCounter]=active_player;

            if (active_player == 0) {

                counter.setImageResource(R.drawable.yellow);
                active_player = 1;

            } else {

                counter.setImageResource(R.drawable.red);
                active_player = 0;

            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(1110);
            for(int[] winningPosition :winningPositions){
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]]&&
                        gameState[winningPosition[0]] != 2){

                    gameIsActive=false;
                    String winner="Red";
                    if(gameState[winningPosition[0]]==0){
                        winner="Yellow";





                    }


                    TextView winnerMessage=(TextView)findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner+" Has Won!");
                    LinearLayout layout=(LinearLayout)findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);

                }else {
                    boolean gameOver=true;

                        for (int counterState:gameState) {
                            if (counterState == 2) {
                                gameOver = false;

                            }
                        }
                            if(gameOver){
                                TextView winnerMessage=(TextView)findViewById(R.id.winnerMessage);
                                winnerMessage.setText("It's a Draw");
                                LinearLayout layout=(LinearLayout)findViewById(R.id.playAgainLayout);
                                layout.setVisibility(View.VISIBLE);

                            }




                }

            }

        }
    }

    public void playAgain(View view){
        gameIsActive=true;
        LinearLayout layout=(LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);


        for(int i=0;i<gameState.length;i++){

            gameState[i]=2;
        }
        GridLayout gridLyt=(GridLayout)findViewById(R.id.gridLayout);
        for(int i=0;i<gridLyt.getChildCount();i++){
            ((ImageView)gridLyt.getChildAt(i)).setImageResource(0);
        }



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
