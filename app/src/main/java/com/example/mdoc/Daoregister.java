package com.example.mdoc;

public class Daoregister {

    private  String firstname;
    private  String lastname;
    private  String type;
    private  String email;
    private  String nic;
    private  int contactnum;
    private  String password;
    private String Confirmpassword;
    private String medicalregno;
    private String spec;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }



    public Daoregister() {
    }


    //created a overloaded constructor by usng first name
    //and last name by Dinuka


    public Daoregister(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Daoregister(String firstname, String lastname, String nic) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.nic = nic;
    }

    //created an overload constructor by using first,last name, email, nic, phone, medregno by nishiki
    public Daoregister(String firstname, String lastname, String email, int contactnum, String nic, String medicalregno){
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.contactnum = contactnum;
        this.nic = nic;
        this.medicalregno = medicalregno;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public int getContactnum() {
        return contactnum;
    }

    public void setContactnum(int contactnum) {
        this.contactnum = contactnum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpassword() {
        return Confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        Confirmpassword = confirmpassword;
    }

    public String getMedicalregno() {
        return medicalregno;
    }

    public void setMedicalregno(String medicalregno) {
        this.medicalregno = medicalregno;
    }
}
