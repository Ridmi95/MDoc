package com.example.mdoc;

import android.provider.BaseColumns;

import java.sql.Blob;

public final class DatabaseContract {

    private DatabaseContract() {
    }


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

    public static class register implements BaseColumns {

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
        public static String REGISTER_SPEC = "spec";
        public static String REGISTER_QUAL = "qual";


    }

    public static class login implements BaseColumns {


        public static String TABLE_NAME = "login";
        public static String LOGIN_USERNAME = "username";
        public static String LOGIN_PASSWORD = "password";


    }

    public static class Appointment implements BaseColumns {
        public static String TABLE_NAME = "Appointment";
        public static String APPOINTMENT_DOCTORNAME = "doctorname";
        public static String APPOINTMENT_PATIENTNAME = "patientname";
        public static String APPOINTMENT_EMAIL = "email";
        public static String APPOINTMENT_MOBILE = "mobile";
        public static String APPOINTMRNT_PROBLEM = "problem";
        public static String APPOINTMENT_DATE = "date";
    }

    public static class myProfile implements BaseColumns
    {
        public static String TABLE_NAME ="myProfile";
        public static String MYPROFILE_FIRSTNAME = "firstname";
        public static String MYPROFILE_LASTNAME = "lastname";
        public static String MYPROFILE_EMAIL = "email";
        public static String MYPROFILE_NIC = "nic";
        public static String MYPROFILE_CONTACTNUM = "contactnum";




    }
    //Lab table
    public static class lab{
        public static String TABLE_NAME = "RECORD";
        public static String LAB_KEY = "id";
        public static String LAB_NAME = "name";
        public static String LAB_AGE = "age";
        public static String LAB_IMAGE = "image";
    }

}
