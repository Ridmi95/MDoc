package com.example.mdoc;

import android.provider.BaseColumns;

public final class DatabaseContract {

    private DatabaseContract() {
    }

    public static class Specialization implements BaseColumns {
        public static String TABLE_NAME = "Specialization";
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

}
