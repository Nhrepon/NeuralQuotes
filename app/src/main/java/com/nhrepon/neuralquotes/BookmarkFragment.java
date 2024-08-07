package com.nhrepon.neuralquotes;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


public class BookmarkFragment extends Fragment {

    RecyclerView recyclerView;
    DatabaseHelper databaseHelper;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String, String> hashMap;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View bookmarkView = inflater.inflate(R.layout.fragment_bookmark, container, false);

        recyclerView = bookmarkView.findViewById(R.id.recyclerView);
        TextView noData = bookmarkView.findViewById(R.id.noData);

        databaseHelper = new DatabaseHelper(getContext());

        Cursor cursor = databaseHelper.getBookmark();
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

        bookmarkAdapter adapter = new bookmarkAdapter();






        return bookmarkView;
    }

////////////////////////////////////////////////
    private class bookmarkAdapter extends BaseAdapter{

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        return null;
    }
}


}