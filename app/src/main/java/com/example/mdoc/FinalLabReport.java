package com.example.mdoc;

public class FinalLabReport {
    private int id;
    private String name;
    private String rID;
    private byte[] image;


    public FinalLabReport(String name, String rID, byte[] image, int id ) {
        this.name = name;
        this.rID = rID;
        this.image = image;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return rID;
    }

    public void setID(String rID) {
        this.rID = rID;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
