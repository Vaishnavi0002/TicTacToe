package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.RequiresApi;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class SingleActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,player;
    TextView headerText;

    int PLAYER_O = 0;
    int PLAYER_X = 1;
    int clickedbutton = 0, noOfPlayers = 1;

    int activePlayer = PLAYER_X;

    int[] filledPos = {-1,-1,-1,-1,-1,-1,-1,-1,-1};

    boolean isGameActive = true;
    Random r = new Random();
    ArrayList<Button> allbuttons = new ArrayList<Button>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        player = (Button) findViewById(R.id.player);
        player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });

        headerText = findViewById(R.id.header_text);
        headerText.setText("Player's Turn");


        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        allbuttons.add(btn0);
        allbuttons.add(btn1);
        allbuttons.add(btn2);
        allbuttons.add(btn3);
        allbuttons.add(btn4);
        allbuttons.add(btn5);
        allbuttons.add(btn6);
        allbuttons.add(btn7);
        allbuttons.add(btn8);


        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);






    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @Override
    public void onClick(View view) {
        // logic for button press
        if (activePlayer != PLAYER_X)
            Toast.makeText(SingleActivity.this, "This is another player's turn", Toast.LENGTH_LONG).show();
        else {
            if (view == btn0)      clickedbutton = 0;
            if (view == btn1)      clickedbutton = 1;
            if (view == btn2)      clickedbutton = 2;
            if (view == btn3)      clickedbutton = 3;
            if (view == btn4)      clickedbutton = 4;
            if (view == btn5)      clickedbutton = 5;
            if (view == btn6)      clickedbutton = 6;
            if (view == btn7)      clickedbutton = 7;
            if (view == btn8)      clickedbutton = 8;
            if(filledPos[clickedbutton] == -1)  {
                allbuttons.get(clickedbutton).setText("X");
                allbuttons.get(clickedbutton).setBackground(getDrawable(android.R.color.holo_orange_light));
                filledPos[clickedbutton] = activePlayer;
                checkForWin();
                if (isGameActive) {
                    if (noOfPlayers == 1) {
                        activePlayer = PLAYER_O;
                        headerText.setText("Computer's Turn");
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                playComputer();
                            }
                        }, 2000);
                    }
                    if (noOfPlayers == 2)   {

                    }
                }
            }
            else
                Toast.makeText(SingleActivity.this, "This tile is already selected", Toast.LENGTH_LONG).show();
        }




    }
    public void playComputer()
    {
        if (activePlayer != PLAYER_O)
            Toast.makeText(SingleActivity.this, "This is another player's turn", Toast.LENGTH_LONG).show();
        else {
            do {
                clickedbutton = r.nextInt(allbuttons.size());
            }while (filledPos[clickedbutton] != -1);
            allbuttons.get(clickedbutton).setText("0");
            allbuttons.get(clickedbutton).setBackground(getDrawable(android.R.color.holo_green_light));
            filledPos[clickedbutton] = activePlayer;
            checkForWin();
            if (isGameActive) {
                activePlayer = PLAYER_X;
                headerText.setText("Player's Turn");
            }
        }
    }

    private void checkForWin(){
        //chech winner

        int[][] winningPos = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

        for (int i = 0; i < 8; i++) {
            int val0 = winningPos[i][0];
            int val1 = winningPos[i][1];
            int val2 = winningPos[i][2];

            if (filledPos[val0] == filledPos[val1] && filledPos[val1] == filledPos[val2]) {
                if (filledPos[val0] != -1) {
                    //winner declare
                    isGameActive = false;
                    if (filledPos[val0] == PLAYER_O)
                        showDialog("O is winner");
                    else
                        showDialog("X is winner");
                }
            }
        }
        // draw match
        int count=0;
        for(int j=0;j<9;j++) {
            if(filledPos[j] != -1) {
                count++;
            }
        }
        if(count==9) {
            showDialog(" Oops! Match is drawn");
        }



    }

    private void showDialog(String winnerText){
        new AlertDialog.Builder(this)
                .setTitle(winnerText)
                .setPositiveButton("Restart game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        restartGame();
                    }
                })
                .show();
    }

    private void restartGame(){
        activePlayer = PLAYER_X;
        headerText.setText("Player's Turn");
        filledPos = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1};
        btn0.setText("");
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");

        btn0.setBackground(getDrawable(android.R.color.darker_gray));
        btn1.setBackground(getDrawable(android.R.color.darker_gray));
        btn2.setBackground(getDrawable(android.R.color.darker_gray));
        btn3.setBackground(getDrawable(android.R.color.darker_gray));
        btn4.setBackground(getDrawable(android.R.color.darker_gray));
        btn5.setBackground(getDrawable(android.R.color.darker_gray));
        btn6.setBackground(getDrawable(android.R.color.darker_gray));
        btn7.setBackground(getDrawable(android.R.color.darker_gray));
        btn8.setBackground(getDrawable(android.R.color.darker_gray));
        isGameActive = true;
    }
    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
















}
