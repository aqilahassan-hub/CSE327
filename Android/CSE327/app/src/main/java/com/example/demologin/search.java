package com.example.demologin;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class search extends AppCompatActivity {
    SearchView search;
    ListView list;

    ArrayList<String> lists;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        search=(SearchView)findViewById(R.id.search);
        list= (ListView) findViewById(R.id.list);

        lists=new ArrayList<String>();

        lists.add("doctor");
        lists.add("salary");
        lists.add("speciality");
        lists.add("address");
        lists.add("contact");
        lists.add("email");

        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,lists);
        list.setAdapter(adapter);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {

                adapter.getFilter().filter(s);
                return false;
            }
        });
    }
}
