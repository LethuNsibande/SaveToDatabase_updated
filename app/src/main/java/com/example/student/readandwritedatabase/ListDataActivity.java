package com.example.student.readandwritedatabase;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;

public class ListDataActivity extends AppCompatActivity
{
    ListView lvPeople;

    DataBaseHelper mDataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_data_activity);

        lvPeople = findViewById(R.id.lstPeople);

        mDataBaseHelper = new DataBaseHelper(this);

        populateListView();
    }

    private void populateListView()
    {
        Cursor data = mDataBaseHelper.getData();

        ArrayList<String> lstData = new ArrayList<>();

        while (data.moveToNext())
        {
            lstData.add(data.getString(1));
        }

        ListAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,
                lstData);

        lvPeople.setAdapter(adapter);

        lvPeople.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
               String sName = adapterView.getItemAtPosition(i).toString();

               Cursor data = mDataBaseHelper.getItemId(sName);

               int itemID = -1;

               while (data.moveToNext())
               {
                   itemID = data.getInt(0);
               }
                   if (itemID > -1)
                   {
                       toastMessage("Name found");

                       Intent intent = new Intent(ListDataActivity.this,
                               EditDataActivity.class);
                       intent.putExtra("id", itemID);
                       intent.putExtra("name", sName);
                       startActivity(intent);

                   } else {
                       toastMessage("No ID was associated with the name");
                   }
            }
        });
    }

    public void toastMessage(String _sMessage)
    {
        Toast.makeText(this, _sMessage, Toast.LENGTH_SHORT).show();
    }
}
