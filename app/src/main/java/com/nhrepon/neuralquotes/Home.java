package com.nhrepon.neuralquotes;

import static androidx.core.content.ContextCompat.getSystemService;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Home extends AppCompatActivity {

    DrawerLayout drawerLayout;
    MaterialToolbar materialToolbar;
    FrameLayout frameLayout;
    NavigationView navigationView;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawerLayout);
        materialToolbar = findViewById(R.id.materialToolbar);
        frameLayout = findViewById(R.id.frameLayout);
        navigationView = findViewById(R.id.navigationView);

        databaseHelper = new DatabaseHelper(getApplicationContext());





        // Toggle drawer /////////////////////////////////////////////////////
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                Home.this, drawerLayout, materialToolbar, R.string.drawerClose, R.string.drawerOpen
        );
        drawerLayout.addDrawerListener(toggle);



        //Initial fragment loading ... ////////////////////////////////////
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frameLayout, new HomeFragment());
        fragmentTransaction.commit();

        //Navigation ////////////////////////////////////////////////////////////////
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.home){

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.frameLayout, new HomeFragment());
                    fragmentTransaction.commit();

                } else if(item.getItemId() == R.id.trending){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.frameLayout, new TrendingFragment());
                    fragmentTransaction.commit();
                } else if(item.getItemId() == R.id.bookmark){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.frameLayout, new BookmarkFragment());
                    fragmentTransaction.commit();
                }else if(item.getItemId() == R.id.addQuote){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.frameLayout, new AddQuoteFragment());
                    fragmentTransaction.commit();
                }

                return true;
            }
        });
        /////////////////////////////////


        //Check network ////////////////////////////////////
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){

            Cursor cursor = databaseHelper.getAllQuote();
            if (cursor == null || cursor.getCount() < 1){
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                String jsonUrl = "https://script.googleusercontent.com/macros/echo?user_content_key=LE5std_xiWtbYX4RkUMqosr2PJbnjapDKaYCGoBJ6brWTvQzpb2py7_yyVm8gVxFKuaYZQmRa_EtZSv2tuPbMc00lq3zXnKkm5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnGXi5Mc8wdtn8nKNqQRKSlPWj4QVcBck6rHumLL58GfbPlvYufXdeaXGhtVDwnkidoFjLhOUxLVHhXTYH1s-GimCNHN9qiWy2Q&lib=MGGxKNuDsQw7AUhEuO00D-AHl4vDo9lPg";
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, jsonUrl, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (Integer i=0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String quote = jsonObject.getString("quote");
                                String author = jsonObject.getString("author");
                                String category = jsonObject.getString("category");
                                String image = jsonObject.getString("image");

                                databaseHelper.addQuotes(quote, author, category, image);
                                Toast.makeText(getApplicationContext(), "Quote add success...", Toast.LENGTH_SHORT).show();

                                //Initial fragment loading ... ////////////////////////////////////
                                FragmentManager fragmentManager = getSupportFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.add(R.id.frameLayout, new HomeFragment());
                                fragmentTransaction.commit();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Something is wrong!", Toast.LENGTH_SHORT).show();
                    }
                });
                requestQueue.add(jsonArrayRequest);
            }


            //Toast.makeText(getApplicationContext(), "Network connected ", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(), "Please, enable network to load data.", Toast.LENGTH_SHORT).show();
        }








    }


    ////// Confirm exit /////////////////////////////////////////////////////////////

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        //finishAffinity();
        new AlertDialog.Builder(Home.this)
                .setTitle("Confirm Exit?")
                .setMessage("Press 'Yes' to exit.")
                .setNegativeButton(
                        "No, Thanks!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }
                )
                .setPositiveButton("Yes, Exit now!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finishAffinity();
                        finish();
                    }
                })
                .show();

    }
}


