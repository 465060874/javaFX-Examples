package com.app.bookmanagement1.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Book {
    private StringProperty name;
    private StringProperty author;
    private DoubleProperty price;

    public Book(String name, String author, double price) {
        this.name = new SimpleStringProperty(name);
        this.author = new SimpleStringProperty(author);
        this.price = new SimpleDoubleProperty(price);
    }

    public String getName() {
        return name.get();
    }

    public double getPrice() {
        return price.get();
    }

    public String getAuthor() {
        return author.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty authorProperty() {
        return author;
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setName(String text) {
        name.set(text);
    }

    public void setAuthor(String text) {
        author.set(text);
    }

    public void setPrice(double v) {
        price.set(v);
    }
}
