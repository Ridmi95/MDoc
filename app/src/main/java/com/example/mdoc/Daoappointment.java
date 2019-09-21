package com.example.mdoc;

public class Daoappointment {

    private String doctorname;
    private String patientname;
    private String email;
    private String mobile;
    private String problems;
    private String Date;

    public Daoappointment(String patientname, String mobile, String problems, String time, String date, String keyword) {
    }


    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    private String Time;

    public Daoappointment() {
    }
    public Daoappointment(String patientname, String mobile, String prob, String date) {
        this.patientname = patientname;
        this.mobile = mobile;
        this.problems = prob;
        this.Date = date;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public String getPatientname() {
        return patientname;
    }

    public void setPatientname(String patientname) {
        this.patientname = patientname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getProblems() {
        return problems;
    }

    public void setProblems(String problems) {
        this.problems = problems;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
