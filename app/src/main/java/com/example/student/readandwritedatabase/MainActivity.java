package com.example.student.readandwritedatabase;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    EditText txtInputOutput;

    Button btnAdd;
    Button btnViewData;

    DataBaseHelper mDataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        btnViewData = findViewById(R.id.btnViewData);

        txtInputOutput = findViewById(R.id.txtInputOutput);

        mDataBaseHelper = new DataBaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String  sNewEntry = txtInputOutput.getText().toString();

                if(txtInputOutput.length() != 0)
                {
                    AddData(sNewEntry);
                    txtInputOutput.setText("");
                }
                else
                {
                    toastMessage("You must put something in the textbox");
                }
            }
        });

        btnViewData.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent data = new Intent(MainActivity.this,ListDataActivity.class);
                startActivity(data);
            }
        });

    }

    public void AddData(String _NewEntry)
    {
        boolean isDataInserted = mDataBaseHelper.addData(_NewEntry);

        if(isDataInserted)
        {
            toastMessage("Data was Successfully Inserted");
        }
        else
        {
            toastMessage("Something went wrong");
        }
    }

    public void toastMessage(String _sMassage)
    {
        Toast.makeText(this, _sMassage, Toast.LENGTH_SHORT).show();
    }
}
