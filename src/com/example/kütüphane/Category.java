package com.example.kütüphane;

public class Category {
    private String name;
    private int id;


    public Category(int id, String name) {
        this.name = name;
        this.id = id;

    }

    public Category() {

    }

    public String getName() {
        return name;
    }

}
