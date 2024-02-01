package com.example.kütüphane;

import java.util.*;

public class User {

    private String name;
    private int borrowedBooksCount;
    public ArrayList borrowedItems;
    private Map<Book, Integer> bookRatings;

    public User(int id, String name) {

        this.borrowedBooksCount = 0;
        this.borrowedItems = new ArrayList();
        this.bookRatings = new HashMap<>();
        this.name =  name;
    }

    public boolean canBorrow() {
        return borrowedBooksCount < 5;  // en fazla 5 kitap ödünç alınabilir
    }

    //@Override
    public ArrayList getBorrowedBooks() {
        Iterator<Object> iterator = borrowedItems.iterator();
        while (iterator.hasNext()) {
            Object item = iterator.next();
            if (item instanceof Integer) {
                iterator.remove();
            }
        }
        ArrayList arrayList = new ArrayList(borrowedItems);
        return arrayList;
    }

    public boolean borrowBook(Book book) {
        if (!borrowedItems.contains(book) && canBorrow()) {
            borrowedItems.add(book.getId());
            borrowedBooksCount++;

            return true;
        }
        return false;
    }
    public void returnItem(Book book) {
        if (borrowedItems.contains(book)) {
            borrowedItems.remove(book);
            borrowedBooksCount--;
        }
    }
    public void rateBook(Book book, int rating) {
        if (rating >= 0 && rating <= 5) {
            bookRatings.put(book, rating);
        }
    }

    public boolean bookHasBorrowed(Book book) {
        return borrowedItems.contains(book);
    }
    public String getName() {
        return name;
    }

}


