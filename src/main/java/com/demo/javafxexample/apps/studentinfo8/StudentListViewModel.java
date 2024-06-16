package com.demo.javafxexample.apps.studentinfo8;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentListViewModel {
    private ObservableList<Student> students = FXCollections.observableArrayList();

    public StudentListViewModel() {
        // Add sample data
        students.add(new Student("John Doe", "A1", "123 Main St"));
        students.add(new Student("Jane Smith", "B2", "456 Elm St"));
    }

    public ObservableList<Student> getStudents() {
        return students;
    }
}
