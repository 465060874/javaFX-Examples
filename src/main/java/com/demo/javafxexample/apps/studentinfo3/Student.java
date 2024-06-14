package com.demo.javafxexample.apps.studentinfo3;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {
    private StringProperty name;
    private StringProperty className;
    private StringProperty address;

    public Student(String name, String className, String address) {
        this.name = new SimpleStringProperty(name);
        this.className = new SimpleStringProperty(className);
        this.address = new SimpleStringProperty(address);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getClassName() {
        return className.get();
    }

    public StringProperty classNameProperty() {
        return className;
    }

    public void setClassName(String className) {
        this.className.set(className);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    // getter和setter方法...
}
