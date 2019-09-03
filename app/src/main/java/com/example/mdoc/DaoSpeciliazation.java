package com.example.mdoc;

public class DaoSpeciliazation {

    //creating variables for specialization
    private int specializationId;
    private String specializationName;
    private String specializationDepartment;
    private String specializationDescription;

    public DaoSpeciliazation() {
    }

    //getters and setters

    public int getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(int specializationId) {
        this.specializationId = specializationId;
    }

    public String getSpecializationName() {
        return specializationName;
    }

    public void setSpecializationName(String specializationName) {
        this.specializationName = specializationName;
    }

    public String getSpecializationDepartment() {
        return specializationDepartment;
    }

    public void setSpecializationDepartment(String specializationDepartment) {
        this.specializationDepartment = specializationDepartment;
    }

    public String getSpecializationDescription() {
        return specializationDescription;
    }

    public void setSpecializationDescription(String specializationDescription) {
        this.specializationDescription = specializationDescription;
    }


    //method implementation need to be done

    public boolean addSpecialzation(){

        return true;
    }

    public boolean updateSpecialzation()
    {
        return true;
    }

    public boolean deleteSpecialization()
    {
        return true;
    }




}
