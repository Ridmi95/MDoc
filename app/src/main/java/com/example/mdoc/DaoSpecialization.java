package com.example.mdoc;

public class DaoSpecialization {

    private int id;
    private String specializationName;
    private String specializationDepartment;
    private String specializationDescription;

    public DaoSpecialization() {
    }

    public DaoSpecialization(String specializationName) {
        this.specializationName = specializationName;
    }

    public DaoSpecialization(int id, String specializationName, String specializationDepartment, String specializationDescription) {
        this.id = id;
        this.specializationName = specializationName;
        this.specializationDepartment = specializationDepartment;
        this.specializationDescription = specializationDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
