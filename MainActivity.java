package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn, btnn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTwoActivity();
            }
        });
        btnn = (Button) findViewById(R.id.btnn);
        btnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSingleActivity();
            }
        });
}
    public void openSingleActivity(){
        Intent intent = new Intent(this, SingleActivity.class);
        startActivity(intent);
    }
    public void openTwoActivity(){
        Intent intent = new Intent(this, TwoActivity.class);
        startActivity(intent);
    }
}

