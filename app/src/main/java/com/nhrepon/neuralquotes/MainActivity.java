package com.nhrepon.neuralquotes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    TextView welcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcome=findViewById(R.id.welcome);

        Thread thread=new Thread(){
            public void run() {
                try {
                    sleep(1000);
                    startActivity(new Intent(MainActivity.this, Home.class));
                }catch ( Exception e ) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

//        welcome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, Home.class));
//            }
//        });





    }
}