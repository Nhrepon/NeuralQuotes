package com.nhrepon.neuralquotes;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Single_quotes extends AppCompatActivity {

    public static String quoteTitles = "a";
    public static String quoteAuthors = "a";



    TextView quoteTitle, quoteAuthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_single_quotes);

        quoteTitle = findViewById(R.id.quoteTitle);
        quoteAuthor = findViewById(R.id.quoteAuthor);

        quoteTitle.setText("“ " + quoteTitles +" ”");
        quoteAuthor.setText("- "+ quoteAuthors);

    }
}