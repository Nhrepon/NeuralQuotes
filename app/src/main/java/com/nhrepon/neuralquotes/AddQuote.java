package com.nhrepon.neuralquotes;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class AddQuote extends AppCompatActivity {

    EditText inputQuote, inputAuthor, inputCategory, inputImage;
    Button submit;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_quote);

        inputQuote = findViewById(R.id.inputQuote);
        inputAuthor = findViewById(R.id.inputAuthor);
        inputCategory = findViewById(R.id.inputCategory);
        inputImage = findViewById(R.id.inputImage);
        submit=findViewById(R.id.submit);

        dbHelper=new DatabaseHelper(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quotes = String.valueOf(inputQuote.getText());
                String authors = inputAuthor.getText().toString();
                String categorys = inputCategory.getText().toString();
                String image = inputImage.getText().toString();

                if(quotes.length() <= 0) {
                    Toast.makeText(getApplicationContext(), "Enter quotes", Toast.LENGTH_LONG).show();
                }else if(authors.length() <= 0){
                    Toast.makeText(getApplicationContext(), "Enter author", Toast.LENGTH_LONG).show();
                }else if(categorys.length() <= 0){
                    Toast.makeText(getApplicationContext(), "Enter category", Toast.LENGTH_LONG).show();
                }else {

                    dbHelper.addQuotes(quotes, authors, categorys, image);
                    Toast.makeText(getApplicationContext(), "Quote add successful...", Toast.LENGTH_SHORT).show();

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.frameLayout, new HomeFragment());
                    fragmentTransaction.commit();
                }
            }
        });


    }


    ///////////////////////////////////


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}