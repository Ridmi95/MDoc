package com.example.mdoc;

import android.provider.BaseColumns;

public final class DatabaseContract {

    private DatabaseContract() {}

    public static class Specialization
    {
        public static String TABLE_NAME = "Specialization";
        public static String SPECIALIZATION_KEY = "Key";
        public static String SPECIALIZATION_NAME = "Name";
        public static String SPECIALIZATION_DEPARTMENT = "Department";
        public static String SPECIALIZATION_DESCRIPTION = "Description";
    }

}
