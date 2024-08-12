package com.nhrepon.neuralquotes;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class Single_quotes extends AppCompatActivity {

    public static String quoteTitles = "a";
    public static String quoteAuthors = "a";
    public static String quoteImage = "https://i.pinimg.com/564x/74/46/aa/7446aaa37a2429fc96ef7e15e4269427.jpg";


    LinearLayout singleQuoteImg;
    TextView quoteTitle, quoteAuthor;
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_single_quotes);


        singleQuoteImg = findViewById(R.id.singleQuoteImg);
        quoteTitle = findViewById(R.id.quoteTitle);
        quoteAuthor = findViewById(R.id.quoteAuthor);
        bottomNavigation = findViewById(R.id.bottomNavigation);


        quoteTitle.setText("“" + quoteTitles +"”");
        quoteAuthor.setText("― "+ quoteAuthors);


        Picasso.get().load(quoteImage).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                //singleQuoteImg.setImageDrawable(new BitmapDrawable(bitmap));
                singleQuoteImg.setBackgroundDrawable(new BitmapDrawable(bitmap));
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                Toast.makeText(getApplicationContext(), "Error : loading wallpaper", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
        //Picasso.get().load(quoteImage).into(singleQuoteImg);



        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.bottomNavBookmark){
                    Toast.makeText(getApplicationContext(), "Quote added to bookmark!", Toast.LENGTH_SHORT).show();
                }else if (item.getItemId() == R.id.bottomNavSave){
                    Toast.makeText(getApplicationContext(), "Quote save success!", Toast.LENGTH_SHORT).show();
                }else if (item.getItemId() == R.id.bottomNavCopy){
                    Toast.makeText(getApplicationContext(), "Quote copy to clipboard!", Toast.LENGTH_SHORT).show();
                }else if (item.getItemId() == R.id.bottomNavShare){
                    Toast.makeText(getApplicationContext(), "Share quote", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }
}