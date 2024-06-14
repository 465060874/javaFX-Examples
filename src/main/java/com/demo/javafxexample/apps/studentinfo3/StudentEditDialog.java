package com.demo.javafxexample.apps.studentinfo3;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class StudentEditDialog extends Dialog<Student> {
    private TextField nameField;
    private TextField classNameField;
    private TextField addressField;
    private StudentViewModel viewModel;

    public StudentEditDialog(StudentViewModel viewModel) {
        this.viewModel = viewModel;
        setTitle("Edit Student");
        initializeUI();
        setupBindings();
    }

    private void initializeUI() {
        nameField = new TextField();
        classNameField = new TextField();
        addressField = new TextField();

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        GridPane gridPane = new GridPane();
        gridPane.add(new Label("Name:"), 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(new Label("Class:"), 0, 1);
        gridPane.add(classNameField, 1, 1);
        gridPane.add(new Label("Address:"), 0, 2);
        gridPane.add(addressField, 1, 2);

        getDialogPane().setContent(gridPane);

        setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                return new Student(nameField.getText(), classNameField.getText(), addressField.getText());
            }
            return null;
        });
    }

    private void setupBindings() {
        Student selectedStudent = viewModel.selectedStudentProperty().get();
        if (selectedStudent != null) {
            nameField.setText(selectedStudent.getName());
            classNameField.setText(selectedStudent.getClassName());
            addressField.setText(selectedStudent.getAddress());
        }

        setOnCloseRequest(event -> {
            if (getResult() != null) {
                viewModel.updateStudent(getResult());
            }
        });
    }
}
