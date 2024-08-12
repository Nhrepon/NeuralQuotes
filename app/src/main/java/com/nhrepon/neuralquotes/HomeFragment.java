package com.nhrepon.neuralquotes;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class HomeFragment extends Fragment {

RecyclerView recyclerView;
DatabaseHelper databaseHelper;
ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
HashMap<String, String> hashMap;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = fragmentView.findViewById(R.id.recyclerView);
        TextView noData = fragmentView.findViewById(R.id.noData);

        databaseHelper=new DatabaseHelper(getContext());



        Cursor cursor = databaseHelper.getAllQuote();
        if (cursor != null && cursor.getCount() > 0){
            while (cursor.moveToNext()){
                Integer id = cursor.getInt(0);
                String quote = cursor.getString(1);
                String author = cursor.getString(2);
                String category = cursor.getString(3);
                String image = cursor.getString(4);

                hashMap = new HashMap<>();
                hashMap.put("id", ""+id);
                hashMap.put("quote", ""+quote);
                hashMap.put("author", ""+author);
                hashMap.put("category", ""+category);
                hashMap.put("image", ""+image);
                arrayList.add(hashMap);
            }
        }else {
            noData.setVisibility(View.VISIBLE);
        }

        



        recycleAdapter adapter = new recycleAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return fragmentView;
    }

    //////////////////////////////////////////////////////////////////
    private class recycleAdapter extends RecyclerView.Adapter<recycleAdapter.recyleViewHolder>{



        // manual create
        private class recyleViewHolder extends RecyclerView.ViewHolder{

            LinearLayout quotesItem;
            TextView category, quote, author;


            public recyleViewHolder(@NonNull View itemView) {
                super(itemView);
                quotesItem = itemView.findViewById(R.id.quotesItem);
                category = itemView.findViewById(R.id.category);
                quote = itemView.findViewById(R.id.quote);
                author = itemView.findViewById(R.id.author);
            }
        }///////





        @NonNull
        @Override
        public recycleAdapter.recyleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


            LayoutInflater inflater = getLayoutInflater();
            View quoteView =inflater.inflate(R.layout.quotes_item, parent, false);


            return new recyleViewHolder(quoteView);
        }

        @Override
        public void onBindViewHolder(@NonNull recycleAdapter.recyleViewHolder holder, int position) {

            hashMap = arrayList.get(position);
            String id = hashMap.get("id");
            String quote = hashMap.get("quote");
            String author = hashMap.get("author");
            String category = hashMap.get("category");
            String image = hashMap.get("image");




            holder.quote.setText(quote);
            holder.author.setText("- " + author);
            holder.category.setText("Category: " + category);

            holder.quotesItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Single_quotes.quoteTitles = quote;
                    Single_quotes.quoteAuthors = author;
                    Single_quotes.quoteImage = image;


                    startActivity(new Intent(getActivity(), Single_quotes.class));
                }
            });
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }
    }/////////////////////















}