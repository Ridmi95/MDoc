package com.example.mdoc;

public class LanModel {
    private int id;
    private String name;
    private String age;

    private byte[] image;

    public LanModel(int id, String name, String age, byte[] image) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.image = image;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {

        this.age = age;
    }




    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {

        this.image = image;
    }
}
