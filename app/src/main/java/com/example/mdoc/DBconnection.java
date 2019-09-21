package com.example.mdoc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
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
        super(context, DATABASE_NAME, null, 6);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //place of practice
        String CREATE_TABLE_POP ="CREATE TABLE "
                + DatabaseContract.Entry.table_name + "(" +
                DatabaseContract.Entry.col_1+ " INTEGER PRIMARY KEY AUTOINCREMENT," + //id
                DatabaseContract.Entry.col_2 + " TEXT," + //hopitalname
                DatabaseContract.Entry.col_3 + " TEXT," + //address
                DatabaseContract.Entry.col_4 + " TEXT," + //contact number
                DatabaseContract.Entry.col_5 + " TEXT," + //day
                DatabaseContract.Entry.col_6 + " TEXT" +")"; //time

        sqLiteDatabase.execSQL(CREATE_TABLE_POP);


       

        String SQL_CREATE_SPECIALIZATION = "CREATE TABLE " + DatabaseContract.Specialization.TABLE_NAME + " (" + DatabaseContract.Specialization.SPECIALIZATION_KEY + "INTEGER PRIMARY KEY,"

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
                +DatabaseContract.register.REGISTER_SPEC +" TEXT,"
                +DatabaseContract.register.REGISTER_STATUS +" TEXT"
                + ")";

        sqLiteDatabase.execSQL(SQL_CREATE_REGISTER);

        String SQL_CREATE_LOGIN =" CREATE TABLE " +DatabaseContract.login.TABLE_NAME +"("
                                 +DatabaseContract.login._ID + " INTEGER PRIMARY KEY, "
                                 +DatabaseContract.login.LOGIN_USERNAME + " TEXT,"
                                 +DatabaseContract.login.LOGIN_PASSWORD + " TEXT" +")";

        sqLiteDatabase.execSQL(SQL_CREATE_LOGIN);


        /*-------------------------LAB REPORT---------------------------*/

        String SQL_CREATE_LAB = " CREATE TABLE " +DatabaseContract.lab.TABLE_NAME+ " ( "
                +DatabaseContract.lab.LAB_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +DatabaseContract.lab.LAB_NAME+" VARCHAR, "+DatabaseContract.lab.LAB_AGE+" VARCHAR, "+DatabaseContract.lab.LAB_IMAGE+ " BLOB "+" ) ";

//        sqLiteDatabase.execSQL(SQL_CREATE_LAB);

/***************************************Appointment Table ********************/
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

    //*************************add POP*************************************************//

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


 //*****************************************update profile********************************************//
    public void updateProfileDetails(Daoregister registerUpdate){
        SQLiteDatabase sqldb = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.register.REGISTER_FIRSTNAME, registerUpdate.getFirstname());
        values.put(DatabaseContract.register.REGISTER_LASTNAME, registerUpdate.getLastname());
        values.put(DatabaseContract.register.REGISTER_EMAIL, registerUpdate.getEmail());
        values.put(DatabaseContract.register.REGISTER_CONTACTNUM, registerUpdate.getContactnum());
        values.put(DatabaseContract.register.REGISTER_REGISTRATIONNO, registerUpdate.getMedicalregno());

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

    public long updateProfile(Daoregister register){
        SQLiteDatabase sd = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.register.REGISTER_FIRSTNAME,register.getFirstname());
        values.put(DatabaseContract.register.REGISTER_LASTNAME,register.getLastname());
        values.put(DatabaseContract.register.REGISTER_EMAIL,register.getEmail());
        values.put(DatabaseContract.register.REGISTER_CONTACTNUM,register.getContactnum());

        long result = sd.update(DatabaseContract.register.TABLE_NAME,values,DatabaseContract.register.REGISTER_NIC + " =? ",new String[] {register.getNic()});


        return result;

    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //******************************************update doctor profile
    public long updateDoctor(Daoregister reg){
        SQLiteDatabase sd = getWritableDatabase();
        ContentValues val = new ContentValues();

        val.put(DatabaseContract.register.REGISTER_NIC, reg.getNic());
        val.put(DatabaseContract.register.REGISTER_FIRSTNAME, reg.getFirstname());
        val.put(DatabaseContract.register.REGISTER_LASTNAME, reg.getLastname());
        val.put(DatabaseContract.register.REGISTER_EMAIL, reg.getEmail());
        val.put(DatabaseContract.register.REGISTER_CONTACTNUM, reg.getContactnum());
        val.put(DatabaseContract.register.REGISTER_REGISTRATIONNO,reg.getMedicalregno());
        val.put(DatabaseContract.register.REGISTER_SPEC,reg.getSpec());
        val.put(DatabaseContract.register.REGISTER_STATUS, reg.getStatus());

        long result = sd.update(DatabaseContract.register.TABLE_NAME,val,DatabaseContract.register.REGISTER_NIC + " =? ",new String[] {reg.getNic()});


        return result;

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //doctor details


    public Daoregister getDocDetails(String userEmail)
    {
        SQLiteDatabase sql = getReadableDatabase();
        String[] projection = {DatabaseContract.register.REGISTER_NIC,DatabaseContract.register.REGISTER_FIRSTNAME,DatabaseContract.register.REGISTER_LASTNAME,DatabaseContract.register.REGISTER_EMAIL,DatabaseContract.register.REGISTER_CONTACTNUM};
        Cursor cursor = sql.query(DatabaseContract.register.TABLE_NAME,projection,DatabaseContract.register.REGISTER_EMAIL + " =? ",new String[]{userEmail},null,null,null);
        Daoregister userProfile = new Daoregister();

        while (cursor.moveToNext()){
            String nic = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.register.REGISTER_NIC));
            String fname = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.register.REGISTER_FIRSTNAME));
            String lname = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.register.REGISTER_LASTNAME));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.register.REGISTER_EMAIL));
            String mobile = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.register.REGISTER_CONTACTNUM));

            userProfile.setFirstname(fname);
            userProfile.setLastname(lname);
            userProfile.setEmail(email);
            userProfile.setNic(nic);
            userProfile.setContactnum(Integer.parseInt(mobile));

        }

        return userProfile;

    }

    //******************************************search patients by date

    public List<Daoappointment> search(String keyword) {

        Daoappointment app = new Daoappointment();
        SQLiteDatabase sql = getReadableDatabase();
        String[] projection = {DatabaseContract.Appointment.APPOINTMENT_PATIENTNAME,DatabaseContract.Appointment.APPOINTMENT_MOBILE, DatabaseContract.Appointment.APPOINTMRNT_PROBLEM, DatabaseContract.Appointment.APPOINTMENT_DATE};
        Cursor cursor = sql.query(DatabaseContract.Appointment.TABLE_NAME, projection, DatabaseContract.Appointment.APPOINTMENT_DATE + " LIKE ?", new String[]{keyword}, null, null, null);
        List<Daoappointment> appoinment = new ArrayList<>();

        while (cursor.moveToNext()){
            String patientName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Appointment.APPOINTMENT_PATIENTNAME));
            String mobile = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Appointment.APPOINTMENT_MOBILE));
            String prob = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Appointment.APPOINTMRNT_PROBLEM));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Appointment.APPOINTMENT_DATE));

            Daoappointment daoappointment =new Daoappointment(patientName, mobile, prob, keyword);
            appoinment.add(daoappointment);
        }
        return appoinment;
    }


    public long updateAppointment(Daoappointment Appoinment){
        SQLiteDatabase sd = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.Appointment.APPOINTMRNT_PROBLEM,Appoinment.getProblems());
        values.put(DatabaseContract.Appointment.APPOINTMENT_DATE,Appoinment.getDate());

        long result = sd.update(DatabaseContract.Appointment.TABLE_NAME,values,DatabaseContract.Appointment.APPOINTMENT_EMAIL  + " =? ",new String[] {Appoinment.getEmail()});

        return result;
    }


    public Daoregister checkLogin(Daoregister daoregister)
    {

        SQLiteDatabase sd = getWritableDatabase();
        String[] projection = {DatabaseContract.register.REGISTER_EMAIL,DatabaseContract.register.REGISTER_PASSWORD,DatabaseContract.register.REGISTER_TYPE};
        String selection = DatabaseContract.register.REGISTER_EMAIL + "=?" + " and " + DatabaseContract.register.REGISTER_PASSWORD + "=?";
        String[] selectionArgs = {daoregister.getEmail(),daoregister.getPassword()};
        Cursor cursor = sd.query(DatabaseContract.register.TABLE_NAME,projection,selection,selectionArgs,null,null,null);
        Daoregister reg = new Daoregister();

        while (cursor.moveToNext())
        {

            if(cursor.getCount() > 0) {
                String type = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.register.REGISTER_TYPE));

                reg.setType(type);
                Log.i("type",type);
                return reg;

            }


        }
        return null;

    }

   public Cursor viewAppointmentData(){

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

    /////////////////////////////////////////////////////////////////////
    //*****************************delete POP***********************************
    public long deletePlaceOfPractice(DaoPlaceOfPractice pop){
        SQLiteDatabase sql = getWritableDatabase();
        long result= sql.delete(DatabaseContract.Entry.table_name,DatabaseContract.Entry.col_4 + " = " + pop.getContact_Number() ,null);

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

   //////////////Nishiki  //////////////////////////////////////////////////////////


  /*----------------------------------------- LAB REPORT DB -----------------------------------------------------*/

    //insertData
    public void insertData(String name, String age, byte[] image){
        SQLiteDatabase database = getWritableDatabase();
        //query to insert record in database table
        String sql = "INSERT INTO RECORD VALUES(NULL, ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindString(2, age);
        statement.bindBlob(3, image);


        statement.executeInsert();
    }

    //updateData
    public void updateData(String name, String age, byte[] image, int id){
        SQLiteDatabase database = getWritableDatabase();
        //query to update record
        String sql = "UPDATE RECORD SET name=?, age=?, image=? WHERE id=?";

        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, name);
        statement.bindString(2, age);

        statement.bindBlob(3, image);
        statement.bindDouble(4, (double)id);

        statement.execute();
        database.close();
    }

    //deleteData
    public void deleteData(int id){
        SQLiteDatabase database = getWritableDatabase();
        //query to delete record using id
        String sql = "DELETE FROM RECORD WHERE id=?";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }





}


