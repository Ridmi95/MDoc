package com.example.mdoc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBconnection extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mDoc.db";
    public DBconnection(Context context) {
        super(context, DATABASE_NAME, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_SPECIALIZATION = "CREATE TABLE " + DatabaseContract.Specialization.TABLE_NAME + " ( " + DatabaseContract.Specialization.SPECIALIZATION_KEY + " INTEGER PRIMARY KEY,"
                                            + DatabaseContract.Specialization.SPECIALIZATION_NAME + " TEXT, "
                                            + DatabaseContract.Specialization.SPECIALIZATION_DEPARTMENT + " TEXT, "
                                            + DatabaseContract.Specialization.SPECIALIZATION_DESCRIPTION + " TEXT" + ");";

        sqLiteDatabase.execSQL(SQL_CREATE_SPECIALIZATION);


        String SQL_CREATE_REGISTER = "CREATE TABLE " + DatabaseContract.register.TABLE_NAME +"("
                                      +DatabaseContract.register.REGISTER_FIRSTNAME +" TEXT, "
                                      +DatabaseContract.register.REGISTER_LASTNAME  + " TEXT, "
                                      +DatabaseContract.register.REGISTER_TYPE  + " TEXT, "
                                      +DatabaseContract.register.REGISTER_NIC   + " STRING PRIMARY KEY, "
                                      +DatabaseContract.register.REGISTER_EMAIL  + " TEXT,"
                                      +DatabaseContract.register.REGISTER_PASSWORD + " TEXT,"
                                      +DatabaseContract.register.REGISTER_CONTACTNUM +" TEXT,"
                                      +DatabaseContract.register.REGISTER_REGISTRATIONNO +" TEXT,"
                                      +DatabaseContract.register.REGISTER_STATUS +" TEXT" + ")";

        sqLiteDatabase.execSQL(SQL_CREATE_REGISTER);

        String SQL_CREATE_LOGIN =" CREATE TABLE " +DatabaseContract.login.TABLE_NAME +"("
                                 +DatabaseContract.login._ID + " INTEGER PRIMARY KEY, "
                                 +DatabaseContract.login.LOGIN_USERNAME + " TEXT,"
                                 +DatabaseContract.login.LOGIN_PASSWORD + " TEXT" +")";

        sqLiteDatabase.execSQL(SQL_CREATE_LOGIN);

    }
    //
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.Specialization.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.register.TABLE_NAME );
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.login.TABLE_NAME);



    }

    public long addNewSpecialization(DaoSpecialization specialization)
    {
        SQLiteDatabase sd = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseContract.Specialization.SPECIALIZATION_KEY,specialization.getId());
        values.put(DatabaseContract.Specialization.SPECIALIZATION_NAME,specialization.getSpecializationName());
        values.put(DatabaseContract.Specialization.SPECIALIZATION_DEPARTMENT,specialization.getSpecializationDepartment());
        values.put(DatabaseContract.Specialization.SPECIALIZATION_DESCRIPTION,specialization.getSpecializationDescription());

        long result = sd.insert(DatabaseContract.Specialization.TABLE_NAME,null,values);
        return result;

    }

    public boolean addregisterInfo(Daoregister register)
    {
        SQLiteDatabase reg = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseContract.register.REGISTER_FIRSTNAME,register.getFirstname());
        values.put(DatabaseContract.register.REGISTER_LASTNAME,register.getLastname());
        values.put(DatabaseContract.register.REGISTER_TYPE,register.getType());
        values.put(DatabaseContract.register.REGISTER_NIC,register.getNic());
        values.put(DatabaseContract.register.REGISTER_EMAIL,register.getEmail());
        values.put(DatabaseContract.register.REGISTER_PASSWORD,register.getPassword());
        values.put(DatabaseContract.register.REGISTER_CONTACTNUM,register.getContactnum());

        long result = reg.insert(DatabaseContract.register.TABLE_NAME,null,values);

        if(result > 0)
        {
            return true;
        }else{

            return false;
        }

    }

    public boolean addloginInfo(Daologin login)
    {

        SQLiteDatabase log = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseContract.login.LOGIN_USERNAME,login.getUsername());
        values.put(DatabaseContract.login.LOGIN_PASSWORD,login.getPassword());

        long resultlog = log.insert(DatabaseContract.login.TABLE_NAME,null,values);

        if(resultlog > 0)
        {
            return true;
        }else{

            return false;
        }

    }

    public List<DaoSpecialization> getAllSpecialization()
    {
        DaoSpecialization specialization = new DaoSpecialization();
        SQLiteDatabase sd= getReadableDatabase();
        String[] projection = {DatabaseContract.Specialization.SPECIALIZATION_NAME,DatabaseContract.Specialization.SPECIALIZATION_DEPARTMENT,DatabaseContract.Specialization.SPECIALIZATION_KEY,DatabaseContract.Specialization.SPECIALIZATION_DESCRIPTION};
        Cursor cursor = sd.query(DatabaseContract.Specialization.TABLE_NAME,projection,null,null,null,null,null);
        List<DaoSpecialization> specializationList = new ArrayList<>();

        while(cursor.moveToNext())
        {
            String specialName= cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Specialization.SPECIALIZATION_NAME));
            String departmentName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Specialization.SPECIALIZATION_DEPARTMENT));
            String specialKey = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Specialization.SPECIALIZATION_KEY));
            String specialDesc = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Specialization.SPECIALIZATION_DESCRIPTION));
            //specializationList.add(specialization);
            DaoSpecialization specialization1 = new DaoSpecialization(Integer.parseInt(specialKey),specialName,departmentName,specialDesc);
            specializationList.add(specialization1);
        }

        return specializationList;
    }

    public Long checkSpecializationExist(String name)
    {
        SQLiteDatabase sd = getReadableDatabase();
        long count = DatabaseUtils.longForQuery(sd, "SELECT COUNT (*) FROM " + DatabaseContract.Specialization.TABLE_NAME + " WHERE " + DatabaseContract.Specialization.SPECIALIZATION_NAME + "=?",new String[] { name });
        return count;
    }


    public long updateSpecialization(DaoSpecialization specialization)
    {
        SQLiteDatabase sd = getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(DatabaseContract.Specialization.SPECIALIZATION_NAME,specialization.getSpecializationName());
        values.put(DatabaseContract.Specialization.SPECIALIZATION_DEPARTMENT,specialization.getSpecializationDepartment());
        long result = sd.update(DatabaseContract.Specialization.TABLE_NAME,values,DatabaseContract.Specialization.SPECIALIZATION_KEY + " =? ",new String[]{String.valueOf(specialization.getId())});
        Log.i("Update Id", DatabaseContract.Specialization.SPECIALIZATION_KEY);
        Log.i("Position ",String.valueOf(specialization.getId()));
        return result;
    }

    public long deleteSpecialization(DaoSpecialization specialization)
    {
        //delete all query
        //long result= sd.delete(DatabaseContract.Specialization.TABLE_NAME,null ,null);

        SQLiteDatabase sd = getWritableDatabase();
        long result= sd.delete(DatabaseContract.Specialization.TABLE_NAME,DatabaseContract.Specialization.SPECIALIZATION_KEY + " = " + specialization.getId() ,null);
        Log.i("Update Id", DatabaseContract.Specialization.SPECIALIZATION_KEY);
        Log.i("Position ",String.valueOf(specialization.getId()));
        return result;
    }

    public List<Daoregister> getAllPaatients()
    {
        SQLiteDatabase sd = getWritableDatabase();
        String[] projection = {DatabaseContract.register.REGISTER_FIRSTNAME,DatabaseContract.register.REGISTER_LASTNAME};

        Cursor cursor = sd.query(DatabaseContract.register.TABLE_NAME,projection,null,null,null,null,null);
        List<Daoregister> patientList = new ArrayList<>();
        while(cursor.moveToNext())
        {
            String firstName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.register.REGISTER_FIRSTNAME));
            String lastName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.register.REGISTER_LASTNAME));
            Daoregister registeredPatient = new Daoregister(firstName,lastName);
            patientList.add(registeredPatient);

        }
        return patientList;
    }




/*
*     public long deleteUser(int position)
    {
        SQLiteDatabase sd = getWritableDatabase();
        long result = sd.delete(DB_Contract.UserMaster.TABLE_NAME,DB_Contract.UserMaster._ID + " = " + (position + 1),null);
        return result;
    }

* */


   /* public long declineDoctorRequest()
    {
        SQLiteDatabase sd = getWritableDatabase();
     //   long result = sd.delete();
        return result;
    }

    public long approveDoctorRequest()
    {
        SQLiteDatabase sd = getWritableDatabase();
        ContentValues values= new ContentValues();
       // values.put();


    //    long result = sd.update();
        return result;
    }*/



}


