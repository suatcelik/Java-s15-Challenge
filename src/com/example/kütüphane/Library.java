package com.example.kütüphane;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private int lastUserId = 0;
    private ArrayList librarians;
    private List<User> users;
    private ArrayList books;
    private ArrayList authors;

    public Library(int id) {
        this.librarians = new ArrayList<>();
        this.users = new ArrayList<>();
        this.books = new ArrayList<>();
        this.authors = new ArrayList<>();
    }



    public void addUser(User user) {
        users.add(user);
    }
    public void addLibrarian(Librarian librarian) {
        librarians.add(librarian);
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Book> getBooks() {
        return books;
    }

    public int generateUserId() {
        return ++lastUserId;
    }

    public List<Book> getBooksByCategory(String categoryName) {
        List<Book> booksInCategory = new ArrayList<>();
        for(Object item : books) {
            if (item instanceof Book) {
                Book b = (Book) item;
                if ((b.getCategory().getName().equalsIgnoreCase(categoryName))){
                    booksInCategory.add(b);
                }
            }
        }
        return booksInCategory;
    }
    public Book getBookById(int bookID) {
        for (Object item : books) {
            if (item instanceof Book) {
                Book book = (Book) item;
                if (book.getId() == bookID) {
                    return book;
                }
            }
        }
        return null;
    }


    public List<Book> getBooksByAuthor(String authorName) {
        List<Book> booksByAuthor = new ArrayList<>();
        for(Object item : books) {
            if (item instanceof Book) {
                Book b = (Book) item;
                String author = b.getAuthor().getTitle();
                if ((author.equals(authorName))){
                    booksByAuthor.add(b);
                }
            }
        }
        return booksByAuthor;
    }

    public Author getAuthorByAuthorName(String authorName) {
        Author authorInfo = null;
        for (Object item : authors) {
            if (item instanceof Author) {
                Author a = (Author) item;
                authorInfo = a;
            }
        }
        return authorInfo;
    }


}
