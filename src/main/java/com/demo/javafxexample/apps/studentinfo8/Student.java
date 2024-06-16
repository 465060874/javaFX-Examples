package com.demo.javafxexample.apps.studentinfo8;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {
    private final StringProperty name;
    private final StringProperty className;
    private final StringProperty address;

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

    public String getClassName() {
        return className.get();
    }

    public StringProperty classNameProperty() {
        return className;
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }
}
