package com.nhrepon.neuralquotes;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


public class AddQuoteFragment extends Fragment {

    TextView inputDataTitle;
    EditText inputQuote, inputAuthor, inputCategory, inputImage;
    Button submit;
    FrameLayout frameLayout;
    DatabaseHelper dbHelper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        View addQuoteView = inflater.inflate(R.layout.fragment_add_quote, container, false);


        inputQuote = addQuoteView.findViewById(R.id.inputQuote);
        inputAuthor = addQuoteView.findViewById(R.id.inputAuthor);
        inputCategory = addQuoteView.findViewById(R.id.inputCategory);
        inputImage = addQuoteView.findViewById(R.id.inputImage);
        submit=addQuoteView.findViewById(R.id.submit);

        dbHelper=new DatabaseHelper(getContext());

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quotes = String.valueOf(inputQuote.getText());
                String authors = inputAuthor.getText().toString();
                String categorys = inputCategory.getText().toString();
                String image = inputImage.getText().toString();

                if(quotes.length() <= 0) {
                    inputQuote.setError("Enter quotes");
                    Toast.makeText(getContext(), "Enter quotes", Toast.LENGTH_SHORT).show();
                }else if(authors.length() <= 0){
                    inputAuthor.setError("Enter a author name");
                    Toast.makeText(getContext(), "Enter a author name", Toast.LENGTH_SHORT).show();
                }else if(categorys.length() <= 0){
                    inputCategory.setError("Select a category");
                    Toast.makeText(getContext(), "Enter category", Toast.LENGTH_SHORT).show();
                }else {

                    dbHelper.addQuotes(quotes, authors, categorys, image);
                    Toast.makeText(getContext(), "Quote add successful...", Toast.LENGTH_SHORT).show();

                    updateUi();

                }
            }
        });



        return addQuoteView;
    }


    public void updateUi(){
        startActivity(new Intent(getContext(), Home.class));
    }


}