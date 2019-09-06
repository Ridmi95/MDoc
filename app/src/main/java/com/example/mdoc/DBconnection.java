package com.example.mdoc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import android.widget.Button;
import android.widget.EditText;

public class DBconnection extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mDoc.db";

    public DBconnection(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_POP ="CREATE TABLE "
                + DatabaseContract.Entry.table_name + "(" +
                DatabaseContract.Entry.col_1+ " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DatabaseContract.Entry.col_2 + " TEXT," +
                DatabaseContract.Entry.col_3 + " TEXT," +
                DatabaseContract.Entry.col_4 + " TEXT," +
                DatabaseContract.Entry.col_5 + " TEXT," +
                DatabaseContract.Entry.col_6 + " TEXT" +")";

        sqLiteDatabase.execSQL(CREATE_TABLE_POP);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.Entry.table_name);
        onCreate(sqLiteDatabase);
    }

    public long insertData(DaoPlaceOfPractice pop){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(DatabaseContract.Entry.col_2, pop.getHospital_Name());
        contentvalues.put(DatabaseContract.Entry.col_3, pop.getAddress());
        contentvalues.put(DatabaseContract.Entry.col_4, pop.getContact_Number());
        contentvalues.put(DatabaseContract.Entry.col_5, pop.getDate());
        contentvalues.put(DatabaseContract.Entry.col_6, pop.getTime());

       long result = db.insert(DatabaseContract.Entry.table_name, null, contentvalues);

       return result;
    }

    //returning information from the database
    public Cursor viewData(){
    SQLiteDatabase sqldb = this.getWritableDatabase() ;
    Cursor result = sqldb.rawQuery("SELECT * FROM "+DatabaseContract.Entry.table_name, null);

    return result;
}
}
