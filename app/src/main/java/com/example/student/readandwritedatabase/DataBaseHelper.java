package com.example.student.readandwritedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper
{
    private static String TABLE_NAME = "people_table";
    private static String COL1 = "ID";
    private static String COL2 = "name";

    public DataBaseHelper(Context context)
    {
        super(context,TABLE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String createTable = "CREATE TABLE " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP IF TABLE EXIST " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String _sItem)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL2, _sItem);

        long iResults = db.insert(TABLE_NAME,null,contentValues);

        if(iResults == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Cursor getData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String sQuery = "SELECT * FROM " + TABLE_NAME;

        Cursor data  = db.rawQuery(sQuery,null);

        return data;
    }

    public Cursor getItemId(String _sName)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String sQuery = "SELECT " + COL1 + " FROM " + TABLE_NAME +" WHERE " +
                COL2 + " = '" + _sName + "'";

        Cursor data  = db.rawQuery(sQuery,null);

        return data;
    }

    public void updateName(String _sNewName, int _iID, String _sOldName)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String sSQL = "UPDATE " + TABLE_NAME + " SET " + COL2 + " = '" + _sNewName +"' " +
                "WHERE " + COL1 + " = '" + _iID + "' AND " + COL2 + " = '" + _sOldName + "'";

        db.execSQL(sSQL);
    }

    public void deleteName(String _sName, int _iID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String sSQL = "DELETE FROM " + TABLE_NAME + " WHERE " + COL1 + " = '" + _iID + "' AND " + COL2 +
                " = '" + _sName + "'";
        db.execSQL(sSQL);
    }
}
