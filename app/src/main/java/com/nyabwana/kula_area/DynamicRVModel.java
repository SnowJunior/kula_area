package com.nyabwana.kula_area;

public class DynamicRVModel {

    String name;
    private final int image;

    public DynamicRVModel(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }
    public  int getImage() {
        return image;
    }
}
