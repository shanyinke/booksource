package com.example.administrator.listviewtest2;

public class Fruit {

    private String name;

    private int imageId;

    private String name1;

    public Fruit(String name, int imageId,String name1) {
        this.name = name;
        this.name1=name1;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }
    public String getName1(){return  name1;}

    public int getImageId() {
        return imageId;
    }

}
