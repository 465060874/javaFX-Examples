package com.demo.javafxexample.apps.studentinfo9;

import javafx.beans.property.SimpleStringProperty;

public class Student {
    private final SimpleStringProperty name;
    private final SimpleStringProperty className;
    private final SimpleStringProperty address;

    public Student(String name, String className, String address) {
        this.name = new SimpleStringProperty(name);
        this.className = new SimpleStringProperty(className);
        this.address = new SimpleStringProperty(address);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getClassName() {
        return className.get();
    }

    public void setClassName(String className) {
        this.className.set(className);
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }
}

