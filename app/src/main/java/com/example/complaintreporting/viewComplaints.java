package com.example.complaintreporting;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class viewComplaints extends AppCompatActivity {

    //private GridView gridView;
    private ArrayList<String> listItem;
    private ArrayAdapter<String> adapter;
    ListView listView;
    MyHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_complaints);
        myDB = new MyHelper(this);
        listView = findViewById(R.id.users_list);
        ArrayList<String> theList=new ArrayList<>();
        Cursor data= myDB.getlistContents();

        if (data.getCount()==0){
            Toast.makeText(this, "Add data please",Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (data.moveToNext()){
                theList.add(data.getString(1));
                ListAdapter listAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                listView.setAdapter(listAdapter);
            }
        }

    }
}
