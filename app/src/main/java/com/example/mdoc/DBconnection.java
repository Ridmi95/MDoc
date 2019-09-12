package com.example.mdoc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import android.content.ContentValues;
import android.database.Cursor;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class DBconnection extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mDoc.db";


   // public DBconnection(Context context) {
   //     super(context, DATABASE_NAME, null, 2);

    public DBconnection(Context context) {
        super(context, DATABASE_NAME, null, 3);

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

        String SQL_CREATE_SPECIALIZATION = "CREATE TABLE " + DatabaseContract.Specialization.TABLE_NAME + " ( " + DatabaseContract.Specialization.SPECIALIZATION_KEY + "INTEGER PRIMARY KEY,"
                                            + DatabaseContract.Specialization.SPECIALIZATION_NAME + " TEXT, "
                                            + DatabaseContract.Specialization.SPECIALIZATION_DEPARTMENT + " TEXT, "
                                            + DatabaseContract.Specialization.SPECIALIZATION_DESCRIPTION + " TEXT" + ");";


        sqLiteDatabase.execSQL(SQL_CREATE_SPECIALIZATION);


        String SQL_CREATE_REGISTER = "CREATE TABLE " + DatabaseContract.register.TABLE_NAME +"("
                                      +DatabaseContract.register.REGISTER_FIRSTNAME +" TEXT, "
                                      +DatabaseContract.register.REGISTER_LASTNAME  + " TEXT, "
                                      +DatabaseContract.register.REGISTER_TYPE  + " TEXT, "
                                      +DatabaseContract.register.REGISTER_NIC   + " TEXT PRIMARY KEY, "
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

        String SQL_CREATE_APPOINTMENT = " CREATE TABLE " +DatabaseContract.Appointment.TABLE_NAME +"("
                                   +DatabaseContract.Appointment._ID + " INTEGER PRIMARY KEY,"
                                   +DatabaseContract.Appointment.APPOINTMENT_DOCTORNAME +" TEXT,"
                                   +DatabaseContract.Appointment.APPOINTMENT_PATIENTNAME +" TEXT,"
                                   +DatabaseContract.Appointment.APPOINTMENT_EMAIL +" TEXT,"
                                   +DatabaseContract.Appointment.APPOINTMENT_MOBILE +" TEXT,"
                                   +DatabaseContract.Appointment.APPOINTMRNT_PROBLEM +" TEXT,"
                                   +DatabaseContract.Appointment.APPOINTMENT_DATE +" TEXT" + ")";

        sqLiteDatabase.execSQL(SQL_CREATE_APPOINTMENT);

    }
    //
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.Entry.table_name);
         sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.Specialization.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.register.TABLE_NAME );
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.login.TABLE_NAME);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.Appointment.TABLE_NAME);

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
    public Cursor ViewData(){
    SQLiteDatabase sqldb = this.getWritableDatabase() ;
    Cursor result = sqldb.rawQuery("SELECT * FROM "+DatabaseContract.Entry.table_name, null);

    return result;
}

//view patients data
    public Cursor ViewPatientsData(){
        SQLiteDatabase sqldb = this.getWritableDatabase() ;
        Cursor result = sqldb.rawQuery("SELECT * FROM "+DatabaseContract.Appointment.TABLE_NAME, null);

        return result;
    }
//delete POP details

 //update profile
    public void updateProfileDetails(Daoregister registerUpdate){
        SQLiteDatabase sqldb = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.register.REGISTER_FIRSTNAME, registerUpdate.getFirstname());
        values.put(DatabaseContract.register.REGISTER_LASTNAME, registerUpdate.getLastname());
        values.put(DatabaseContract.register.REGISTER_EMAIL, registerUpdate.getEmail());
        values.put(DatabaseContract.register.REGISTER_CONTACTNUM, registerUpdate.getContactnum());
        values.put(DatabaseContract.register.REGISTER_REGISTRATIONNO, registerUpdate.getMedicalregno());

//        String updateQuery = "UPDATE " +DatabaseContract.register.TABLE_NAME + " SET " + DatabaseContract.register.REGISTER_FIRSTNAME + " = '" + registerUpdate.getFirstname() +

        sqldb.update(DatabaseContract.register.TABLE_NAME, values, "nic = ?", null);


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

    public  boolean addAppointmentInfo(Daoappointment Appoinment)
    {

        SQLiteDatabase app = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseContract.Appointment.APPOINTMENT_DOCTORNAME,Appoinment.getDoctorname());
        values.put(DatabaseContract.Appointment.APPOINTMENT_PATIENTNAME,Appoinment.getPatientname());
        values.put(DatabaseContract.Appointment.APPOINTMENT_EMAIL,Appoinment.getEmail());
        values.put(DatabaseContract.Appointment.APPOINTMENT_MOBILE,Appoinment.getMobile());
        values.put(DatabaseContract.Appointment.APPOINTMRNT_PROBLEM,Appoinment.getProblems());
        values.put(DatabaseContract.Appointment.APPOINTMENT_DATE,Appoinment.getDate());
        Log.i("name",Appoinment.getDoctorname());
        Log.i("patient",Appoinment.getPatientname());
        Log.i("email",Appoinment.getEmail());
        Log.i("mbile",Appoinment.getMobile());
        Log.i("prob",Appoinment.getProblems());
        Log.i("date",Appoinment.getDate());

        long resultapp = app.insert(DatabaseContract.Appointment.TABLE_NAME,null,values);
        Log.i("result",String.valueOf(resultapp));
        if (resultapp > 0)
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


    public boolean checkLogin(Daoregister daoregister)
    {
        SQLiteDatabase sd = getWritableDatabase();
        String[] projection = {DatabaseContract.register.REGISTER_EMAIL,DatabaseContract.register.REGISTER_PASSWORD};
        String selection = DatabaseContract.register.REGISTER_EMAIL + "=?" + " and " + DatabaseContract.register.REGISTER_PASSWORD + "=?";
        String[] selectionArgs = {daoregister.getEmail(),daoregister.getPassword()};
        Cursor cursor = sd.query(DatabaseContract.register.TABLE_NAME,projection,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        if(count > 0)
        {
            return true;
        }else{
            return false;
        }
    }

   public Cursor viewData(){

        SQLiteDatabase sqldb = this.getWritableDatabase();
        Cursor result = sqldb.rawQuery("SELECT * FROM "+DatabaseContract.Appointment.TABLE_NAME,null);

       return  result;

   }

    public Daoregister getProfileDetails(String userEmail)
    {
        SQLiteDatabase sd = getReadableDatabase();
        String[] projection = {DatabaseContract.register.REGISTER_FIRSTNAME,DatabaseContract.register.REGISTER_LASTNAME,DatabaseContract.register.REGISTER_EMAIL,DatabaseContract.register.REGISTER_NIC,DatabaseContract.register.REGISTER_CONTACTNUM};
        Cursor cursor = sd.query(DatabaseContract.register.TABLE_NAME,projection,DatabaseContract.register.REGISTER_EMAIL + " = " + userEmail,null,null,null,null);
        Daoregister userProfile = new Daoregister();

        while (cursor.moveToNext()){
            String fname = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.register.REGISTER_FIRSTNAME));
            String lname = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.register.REGISTER_LASTNAME));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.register.REGISTER_EMAIL));
            String nic = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.register.REGISTER_NIC));
            String mobile = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.register.REGISTER_CONTACTNUM));

            userProfile.setFirstname(fname);
            userProfile.setLastname(lname);
            userProfile.setEmail(email);
            userProfile.setNic(nic);
            userProfile.setContactnum(Integer.parseInt(mobile));

        }

        return userProfile;

    }

    public Cursor viewDataListofDoctors(){

        SQLiteDatabase sqldbl = this.getWritableDatabase();
        Cursor result = sqldbl.rawQuery("SELECT * FROM "+DatabaseContract.Appointment.TABLE_NAME,null);
        //doctors table
        return  result;

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

    public long getTotalregisteredPatients()
    {
        SQLiteDatabase sd = getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(sd,DatabaseContract.register.TABLE_NAME);
        sd.close();
        return count;
    }

    public List<Daoregister> getAllPendingDoctors()
    {
        SQLiteDatabase sd = getWritableDatabase();
        String[] projection = {DatabaseContract.register.REGISTER_FIRSTNAME,DatabaseContract.register.REGISTER_LASTNAME};

        Cursor cursor = sd.query(DatabaseContract.register.TABLE_NAME,projection,null,null,null,null,null,null);
        List<Daoregister> pendingDoctorList = new ArrayList<>();
        while(cursor.moveToNext())
        {
            String firstName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.register.REGISTER_FIRSTNAME));
            String lastName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.register.REGISTER_LASTNAME));
            Daoregister registeredPatient = new Daoregister(firstName,lastName);
            pendingDoctorList.add(registeredPatient);

        }
        return pendingDoctorList;
    }

    public List<Daoregister> getAllRegisteredDoctors()
    {
        SQLiteDatabase sd = getWritableDatabase();
        String[] projection = {DatabaseContract.register.REGISTER_FIRSTNAME,DatabaseContract.register.REGISTER_LASTNAME};

        Cursor cursor = sd.query(DatabaseContract.register.TABLE_NAME,projection,DatabaseContract.register.REGISTER_STATUS,new String[]{"Registered"},null,null,null,null);
        List<Daoregister> doctorList = new ArrayList<>();
        while(cursor.moveToNext())
        {
            String Nic = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.register.REGISTER_NIC));
            String firstName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.register.REGISTER_FIRSTNAME));
            String lastName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.register.REGISTER_LASTNAME));
            Daoregister registeredPatient = new Daoregister(firstName,lastName,Nic);
            doctorList.add(registeredPatient);

        }
        return doctorList;
    }
 /*  public long declineDoctorRequest()
    {
        SQLiteDatabase sd = getWritableDatabase();
        long result = sd.delete();
        return result;
    }*/

    public long approveDoctorRequest(String name)
    {
        SQLiteDatabase sd = getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(DatabaseContract.register.REGISTER_STATUS,"Registered");
        //String selection = DatabaseContract.register.REGISTER_FIRSTNAME + " " + DatabaseContract.register.REGISTER_LASTNAME;
        //dinuka perera
        long result = sd.update(DatabaseContract.register.TABLE_NAME,values,DatabaseContract.register.REGISTER_FIRSTNAME + " LIKE " + name +"%",null);
        return result;
    }


/*
*     public long deleteUser(int position)
    {
        SQLiteDatabase sd = getWritableDatabase();
        long result = sd.delete(DB_Contract.UserMaster.TABLE_NAME,DB_Contract.UserMaster._ID + " = " + (position + 1),null);
        return result;
    }

* */





}


