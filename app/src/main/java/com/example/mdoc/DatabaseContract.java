package com.example.mdoc;

import android.provider.BaseColumns;

public final class DatabaseContract {

    private DatabaseContract() {}

    public static class Specialization implements BaseColumns
    {
        public static String TABLE_NAME = "Specialization";
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
