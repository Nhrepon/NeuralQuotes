package com.nhrepon.neuralquotes;

import android.content.Intent;
import android.database.Cursor;
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

                hashMap = new HashMap<>();
                hashMap.put("id", ""+id);
                hashMap.put("quote", ""+quote);
                hashMap.put("author", ""+author);
                hashMap.put("category", ""+category);
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

        LinearLayout quotesItem;

        // manual create
        private class recyleViewHolder extends RecyclerView.ViewHolder{

            TextView category, quote, authore;


            public recyleViewHolder(@NonNull View itemView) {
                super(itemView);
                quotesItem = itemView.findViewById(R.id.quotesItem);
                category = itemView.findViewById(R.id.category);
                quote = itemView.findViewById(R.id.quote);
                authore = itemView.findViewById(R.id.authore);
            }
        }///////





        @NonNull
        @Override
        public recycleAdapter.recyleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LinearLayout quotesItem;
            LayoutInflater inflater = getLayoutInflater();
            View quoteView =inflater.inflate(R.layout.quotes_item, parent, false);

            quotesItem = quoteView.findViewById(R.id.quotesItem);
            quotesItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    String quote = hashMap.get("quote");
                    String author = hashMap.get("author");

                    Single_quotes.quoteTitles = quote;
                    Single_quotes.quoteAuthores = author;
                    startActivity(new Intent(getActivity(), Single_quotes.class));
                }
            });
            return new recyleViewHolder(quoteView);
        }

        @Override
        public void onBindViewHolder(@NonNull recycleAdapter.recyleViewHolder holder, int position) {

            hashMap = arrayList.get(position);
            String id = hashMap.get("id");
            String quote = hashMap.get("quote");
            String author = hashMap.get("author");
            String category = hashMap.get("category");



            holder.quote.setText(quote);
            holder.authore.setText(" - " + author);
            holder.category.setText("Category: " + category);
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }
    }/////////////////////












}