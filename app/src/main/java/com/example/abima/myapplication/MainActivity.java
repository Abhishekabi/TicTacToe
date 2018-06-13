package com.example.abima.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public int playerState = 0;
    // 0 = player one
    // 1 = player two
    public int tapcount = 0;
    public int[] blockPoints = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    public void imageTapped(View view) {

        ImageView currentBlock = (ImageView) view;
        int block = Integer.parseInt(currentBlock.getTag().toString());
        if (blockPoints[block] == 2) {
            tapcount++;

            if (playerState == 0) {
                currentBlock.setImageResource(R.drawable.cross);
                playerState = 1;
            } else {
                currentBlock.setImageResource(R.drawable.circle);
                playerState = 0;
            }
            currentBlock.animate().rotationX(360).setDuration(1000);

            blockPoints[block] = playerState;
            if (tapcount >= 5) {
                if (checkWinner() == 1) {
                    Toast.makeText(MainActivity.this, "Player 1 is the  winner", Toast.LENGTH_LONG).show();
                    reset(view);
                }
                else if(checkWinner()==0) {
                    Toast.makeText(MainActivity.this, "Player 2 is the  winner", Toast.LENGTH_LONG).show();
                    reset(view);
                }
            }
        } else
            Toast.makeText(MainActivity.this, "You cannot tap more than once", Toast.LENGTH_SHORT).show();
    }



    private int checkWinner() {
        for (int j = 0; j < 8; j = j + 3) {
            if ((blockPoints[j] == 1 && blockPoints[j + 1] == 1 && blockPoints[j + 2] == 1) || (blockPoints[j] == 0 && blockPoints[j + 1] == 0 && blockPoints[j + 2] == 0)) {
                return blockPoints[j];
            }
        }
        for (int j = 0; j < 3; j++) {
            if ((blockPoints[j] == 1 && blockPoints[j + 3] == 1 && blockPoints[j + 6] == 1) || (blockPoints[j] == 0 && blockPoints[j + 3] == 0 && blockPoints[j + 6] == 0)) {
                return blockPoints[j];
            }
        }
        if (blockPoints[0] == 1) if (blockPoints[4] == 1) if (blockPoints[8] == 1) return 1;
        if (blockPoints[0] == 0) if (blockPoints[4] == 0) if (blockPoints[8] == 0) return 0;
        if (blockPoints[2] == 1) if (blockPoints[4] == 1) if (blockPoints[6] == 1) return 1;
        if (blockPoints[2] == 0) if (blockPoints[4] == 0) if (blockPoints[6] == 0) return 0;
        return -1;
    }


    public void reset(View view) {
        playerState = 0;
        tapcount=0;

        for (int i = 0; i < blockPoints.length; i++) {
            blockPoints[i] = 2;
        }

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.board);
        int noOfRows = linearLayout.getChildCount();

        for (int i = 0; i < noOfRows; i++) {
            LinearLayout row = (LinearLayout) linearLayout.getChildAt(i);
            int rowCount = row.getChildCount();

            for (int j = 0; j < rowCount; j++) {
                ImageView block = (ImageView) row.getChildAt(j);
                block.setImageResource(R.drawable.orange);
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}
