package com.demo.javafxexample.apps.studentinfo9;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentViewModel {
    private ObservableList<Student> students;
    private SimpleObjectProperty<Student> selectedStudent;

    public StudentViewModel() {
        students = FXCollections.observableArrayList();
        students.add(new Student("John Doe", "A1", "123 Main St"));
        students.add(new Student("Jane Smith", "B2", "456 Elm St"));
        selectedStudent = new SimpleObjectProperty<>();
    }

    public ObservableList<Student> getStudents() {
        return students;
    }

    public void setStudents(ObservableList<Student> students) {
        this.students = students;
    }

    public Student getSelectedStudent() {
        return selectedStudent.get();
    }

    public void setSelectedStudent(Student selectedStudent) {
        this.selectedStudent.set(selectedStudent);
    }

    public SimpleObjectProperty<Student> selectedStudentProperty() {
        return selectedStudent;
    }
}

