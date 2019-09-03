package com.example.mdoc;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBconnection extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mDoc.db";
    public DBconnection(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_SPECIALIZATION = "CREATE TABLE " + DatabaseContract.Specialization.TABLE_NAME + " ( "
                                            + DatabaseContract.Specialization._ID + " INTEGER PRIMARY KEY, "
                                            + DatabaseContract.Specialization.SPECIALIZATION_NAME + " TEXT, "
                                            + DatabaseContract.Specialization.SPECIALIZATION_DEPARTMENT + " TEXT,"
                                            + DatabaseContract.Specialization.SPECIALIZATION_DESCRIPTION + " TEXT " + " )";


        sqLiteDatabase.execSQL(SQL_CREATE_SPECIALIZATION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.Specialization.TABLE_NAME);
    }

    public long addNewSpecialization(DaoSpeciliazation specialization)
    {
        SQLiteDatabase sd = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseContract.Specialization.SPECIALIZATION_NAME,specialization.getSpecializationName());
        values.put(DatabaseContract.Specialization.SPECIALIZATION_DEPARTMENT,specialization.getSpecializationDepartment());
        values.put(DatabaseContract.Specialization.SPECIALIZATION_DESCRIPTION,specialization.getSpecializationDescription());

        long result = sd.insert(DatabaseContract.Specialization.TABLE_NAME,null,values);
        return result;

    }

}


