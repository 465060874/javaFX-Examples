package com.app.bookmanagement1.viewmodel;


import com.app.bookmanagement1.model.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class BookViewModel {
    private ObservableList<Book> bookList;

    public BookViewModel() {
        bookList = FXCollections.observableArrayList();
    }

    public void addBook(Book book) {
        bookList.add(book);
    }

    public void updateBook(int index, Book book) {
        bookList.set(index, book);
    }

    public ObservableList<Book> getBookList() {
        return bookList;
    }
}
