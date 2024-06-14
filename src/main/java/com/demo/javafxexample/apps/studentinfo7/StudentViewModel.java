package com.demo.javafxexample.apps.studentinfo7;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentViewModel {
    private ObservableList<Student> students;

    public StudentViewModel() {
        students = FXCollections.observableArrayList(
                new Student("John Doe", "Class A", "123 Main St"),
                new Student("Jane Smith", "Class B", "456 Elm St")
        );
    }

    public ObservableList<Student> getStudents() {
        return students;
    }

    public void updateStudent(Student student, String name, String className, String address) {
        student.nameProperty().set(name);
        student.classNameProperty().set(className);
        student.addressProperty().set(address);
    }
}
