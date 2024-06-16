package com.demo.javafxexample.apps.studentinfo9;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class StudentEditDialogController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField classField;
    @FXML
    private TextField addressField;

    private Student student;
    private StudentViewModel viewModel;

    @FXML
    private void initialize() {
    }

    public void setStudent(Student student) {
        this.student = student;
        nameField.setText(student.getName());
        classField.setText(student.getClassName());
        addressField.setText(student.getAddress());
    }

    public void setViewModel(StudentViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @FXML
    private void handleSave() {
        student.setName(nameField.getText());
        student.setClassName(classField.getText());
        student.setAddress(addressField.getText());
        viewModel.setSelectedStudent(student);
    }
}

