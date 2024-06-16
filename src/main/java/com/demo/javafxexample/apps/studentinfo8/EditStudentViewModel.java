package com.demo.javafxexample.apps.studentinfo8;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EditStudentViewModel {
    private StringProperty name = new SimpleStringProperty();
    private StringProperty className = new SimpleStringProperty();
    private StringProperty address = new SimpleStringProperty();
    private Student student;

    public EditStudentViewModel(Student student) {
        this.student = student;
        setName(student.getName());
        setClassName(student.getClassName());
        setAddress(student.getAddress());
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

    public void updateStudent(String name, String className, String address){
        this.student.nameProperty().set(name);
        this.student.classNameProperty().set(className);
        this.student.addressProperty().set(address);
    }


// Getters and Setters
}
