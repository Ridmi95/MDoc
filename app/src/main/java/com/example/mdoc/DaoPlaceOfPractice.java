package com.example.mdoc;

import android.graphics.drawable.AnimationDrawable;

public class DaoPlaceOfPractice {

    int ID;
    String Hospital_Name;
    String Address;
    String Contact_Number;
    String Date;
    String time;

    public DaoPlaceOfPractice(){}

    public DaoPlaceOfPractice(String Hospital_Name, String Address, String Contact_number){
        this.setHospital_Name(Hospital_Name);
        this.setAddress(Address);
        this.setContact_Number(Contact_number);
    }

    public DaoPlaceOfPractice(String Date, String time){

    }

    //setters
    public void setHospital_Name(String hospital_Name) {
        Hospital_Name = hospital_Name;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setContact_Number(String contact_Number) {
        Contact_Number = contact_Number;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    //getters
    public String getHospital_Name() {
        return this.Hospital_Name;
    }

    public String getAddress() {
        return this.Address;
    }

    public String getContact_Number() {
        return this.Contact_Number;
    }

    public String getDate() {
        return this.Date;
    }

    public String getTime() {
        return this.time;
    }


}
