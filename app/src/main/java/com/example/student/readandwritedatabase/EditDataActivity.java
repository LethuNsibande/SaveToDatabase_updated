package com.example.student.readandwritedatabase;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditDataActivity extends AppCompatActivity
{
    EditText txtInputOutput;

    Button btnDelete;
    Button btnSave;

    DataBaseHelper mDataBaseHelper;

    String sSelectedName;

    int iSelectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data_activity);

        btnDelete = findViewById(R.id.btnDelete);
        btnSave = findViewById(R.id.btnSave);

        txtInputOutput = findViewById(R.id.txtInputOutput);

        mDataBaseHelper = new DataBaseHelper(this);

        sSelectedName = getIntent().getStringExtra("name");

        iSelectedID = getIntent().getIntExtra("id",-1);

        txtInputOutput.setText(sSelectedName);

        btnSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String sNewName = txtInputOutput.getText().toString();

                if (!sNewName.equals("")) {
                    toastMessage("You have successfully updated the name");
                    mDataBaseHelper.updateName(sNewName, iSelectedID, sSelectedName);
                    startActivity(new Intent(getBaseContext(), ListDataActivity.class));
                }
                else
                    toastMessage("You must enter a name");
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String sName = txtInputOutput.getText().toString();
                Cursor data = mDataBaseHelper.getItemId(sName);

                if (!sName.equals("")){
                    toastMessage("You have successfully deleted " + sName);
                    mDataBaseHelper.deleteName(sName, iSelectedID);
                    startActivity(new Intent(getBaseContext(), ListDataActivity.class));
                }
                else{
                    toastMessage("You must enter a name.");
                }
            }
        });
    }
    public void toastMessage(String _sMessage)
    {
        Toast.makeText(this, _sMessage, Toast.LENGTH_SHORT).show();
    }
}
