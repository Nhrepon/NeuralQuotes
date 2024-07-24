package com.nhrepon.neuralquotes;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Single_quotes extends AppCompatActivity {

    public static String quoteTitles = "av";
    public static String quoteAuthores = "ad";
    TextView quoteTitle, quoteAuthore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_single_quotes);

        quoteTitle = findViewById(R.id.quoteTitle);
        quoteAuthore = findViewById(R.id.quoteAuthore);

        quoteTitle.setText(quoteTitles);
        quoteAuthore.setText(" - "+quoteAuthores);

    }
}