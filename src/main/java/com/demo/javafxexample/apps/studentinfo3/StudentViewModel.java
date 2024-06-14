package com.demo.javafxexample.apps.studentinfo3;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//StudentViewModel类(ViewModel):
public class StudentViewModel {
    private ObservableList<Student> students;
    private ObjectProperty<Student> selectedStudent;

    public StudentViewModel() {
        students = FXCollections.observableArrayList();
        selectedStudent = new SimpleObjectProperty<>();
    }

    public ObservableList<Student> getStudents() {
        return students;
    }

    public ObjectProperty<Student> selectedStudentProperty() {
        return selectedStudent;
    }

    public void setSelectedStudent(Student student) {
        selectedStudent.set(student);
    }

    public void updateStudent(Student updatedStudent) {
        int index = students.indexOf(selectedStudent.get());
        if (index != -1) {
            students.set(index, updatedStudent);
        }
    }
}
