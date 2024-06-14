package com.demo.javafxexample.apps.studentinfo7;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StudentEditDialogController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField classField;
    @FXML
    private TextField addressField;

    private Student student;
    private StudentViewModel viewModel;

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
        viewModel.updateStudent(student, nameField.getText(), classField.getText(), addressField.getText());
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }
}
