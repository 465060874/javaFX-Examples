package com.demo.javafxexample.apps.studentinfo5;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 1. 创建Model
 * 首先定义一个学生的模型类，包含姓名、班级和住址字段。
 */
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

    public void setName(String name) {
        this.name.set(name);
    }

    public void setClassName(String className) {
        this.className.set(className);
    }

    public void setAddress(String address) {
        this.address.set(address);
    }
}
