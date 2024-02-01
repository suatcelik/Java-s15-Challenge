package com.example.kütüphane;

import java.util.ArrayList;
import java.util.List;

public class Book extends Category {
    private int id;
    private Author author;
    ;
    private Category category;
    private boolean borrowed;
    private int rating;


    public Book(int id, String name, Author author, Category category, boolean borrowed, int rating) {
        super(id, name);
        this.id = id;
        this.author = author;
        this.category = category;
        this.borrowed = borrowed;
        this.rating = rating;
    }


    public void updateBookInfo(String title, Author author, Category category) {
        this.author = author;
        this.category = category;
    }
    public int getId() {
        return id;
    }

    public Author getAuthor() {
        return author;
    }

    public Category getCategory() {
        return category;
    }

    public int getRating() {
        return rating;
    }
    private ArrayList borrowedItems = new ArrayList();
    public List<Item> getBorrowedItems() {

        return new ArrayList<>(borrowedItems);
    }


    @Override
    public String toString() {
        return "Book : " + getName() + "| "
                + "Category: " + getCategory().getName() + "| "
                + "Rating:  " + getRating() + "| "
                + "Id: " + getId();

    }
}


