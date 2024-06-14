package com.demo.javafxexample.apps.studentinfo4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class StudentViewModel {
    private final ObservableList<Student> students;

    public StudentViewModel() {
        students = FXCollections.observableArrayList();
        // 添加一些示例数据
        students.add(new Student("Alice", "123 Main St"));
        students.add(new Student("Bob", "456 Oak St"));
    }

    public ObservableList<Student> getStudents() {
        return students;
    }

    public void showStudentDetails(Student student) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Student Details");
        alert.setHeaderText("Details for " + student.getName());
        alert.setContentText("Address: " + student.getAddress());
        alert.showAndWait();
    }
}
