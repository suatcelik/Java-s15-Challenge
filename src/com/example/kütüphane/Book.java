package com.example.kütüphane;

import java.util.ArrayList;

public class Book extends Category {
    private int id;
    private String author;
    ;
    private Category category;
    private boolean borrowed;
    private int rating;
    private ArrayList borrowedItems = new ArrayList();

    public Book(int id, String name, int id1, String author, Category category, boolean borrowed, int rating, ArrayList borrowedItems) {
        super(id, name);
        this.id = id1;
        this.author = author;
        this.category = category;
        this.borrowed = borrowed;
        this.rating = rating;
    }




    public Book(int id, String name, String author, Category category) {
        super(id, name);
        this.author = author;
        this.category = category;
    }

    public Book(int id, String name, Author author, Category category, boolean borrowed, int rating) {
        super(id ,name);
    }


    public void setRating(int rating) {
        this.rating = rating;
    }
    public void updateBookInfo(String title, Author author, Category category) {
        this.author = String.valueOf(author);
        this.category = category;
    }

    public int getId() {
        return id;
    }
    public String getAuthor() {
        return author;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public int getRating() {
        return rating;
    }
    public ArrayList getBorrowedItems() {
        return new ArrayList(borrowedItems);
    }

    @Override
    public String toString() {
        return "Book : " + getName() + "| "
                + "Category: " + getCategory().getName() + "| "
                + "Rating:  " + getRating() + "| "
                + "Id: " + getId();

    }
}


