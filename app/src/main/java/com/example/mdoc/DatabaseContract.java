package com.example.mdoc;

import android.provider.BaseColumns;

public final class DatabaseContract {

    private DatabaseContract() {}


    public static class Entry implements BaseColumns {


        public static final String table_name = "placeOfPractice";

        public static final String col_1 = "ID";
        public static final String col_2 = "Hospital_Name";
        public static final String col_3 = "Address";
        public static final String col_4 = "Contact_Number";
        public static final String col_5 = "Date";
        public static final String col_6 = "Time";




///////////////////////////////////////////////////////////////////////////////////////

//        public static final String table_name2 = "Doctor";
//
//        public static final String col_2_1 = "NIC";
//        public static final String col_2_2 = "Title";
//        public static final String col_2_3 = "First_Name";
//        public static final String col_2_4 = "Last_Name";
//        public static final String col_2_5 = "Email";
//        public static final String col_2_6 = "Contact_Number";
//        public static final String col_2_7 = "RegNo";
    }
    public static class Specialization
    {
        public static String TABLE_NAME = "Specialization";
        public static String SPECIALIZATION_KEY = "Key";
        public static String SPECIALIZATION_NAME = "Name";
        public static String SPECIALIZATION_DEPARTMENT = "Department";
        public static String SPECIALIZATION_DESCRIPTION = "Description";
    }

    public static  class register implements  BaseColumns
    {

        public static String TABLE_NAME = "register";
        public static String REGISTER_FIRSTNAME = "firstname";
        public static String REGISTER_LASTNAME = "lastname";
        public static String REGISTER_TYPE = "type";
        public static String REGISTER_EMAIL = "email";
        public static String REGISTER_NIC = "nic";
        public static String REGISTER_CONTACTNUM = "contactnum";
        public static String REGISTER_PASSWORD = "password";
        public static String REGISTER_REGISTRATIONNO = "registrationno";
        public static String REGISTER_STATUS = "status";


    }

    public static class login implements BaseColumns
    {


        public static String TABLE_NAME ="login";
        public static String LOGIN_USERNAME = "username";
        public static String LOGIN_PASSWORD="password";




    }

}
