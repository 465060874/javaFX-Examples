package com.demo.javafxexample.apps.studentinfo6;

//View (StudentView.java):
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StudentView {
    private TableView<Student> table;
    private StudentViewModel viewModel;

    public StudentView(StudentViewModel viewModel) {
        this.viewModel = viewModel;
        createUI();
    }

    private void createUI() {
        table = new TableView<>();
        table.setItems(viewModel.getStudents());

        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Student, String> classColumn = new TableColumn<>("Class");
        classColumn.setCellValueFactory(new PropertyValueFactory<>("className"));

        TableColumn<Student, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn<Student, Void> actionColumn = new TableColumn<>("Action");
        actionColumn.setCellFactory(param -> new TableCell<>() {
            private final Button editButton = new Button("Edit");

            {
                editButton.setOnAction(event -> {
                    Student student = getTableView().getItems().get(getIndex());
                    showEditDialog(student);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : editButton);
            }
        });

        table.getColumns().addAll(nameColumn, classColumn, addressColumn, actionColumn);
    }

    private void showEditDialog(Student student) {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Edit Student");

        TextField nameField = new TextField(student.getName());
        TextField classField = new TextField(student.getClassName());
        TextField addressField = new TextField(student.getAddress());

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        VBox content = new VBox(10);
        content.setPadding(new Insets(10));
        content.getChildren().addAll(
                new Label("Name:"), nameField,
                new Label("Class:"), classField,
                new Label("Address:"), addressField
        );

        dialog.getDialogPane().setContent(content);
        dialog.setResultConverter(buttonType -> {
            if (buttonType == saveButtonType) {
                viewModel.updateStudent(student, nameField.getText(), classField.getText(), addressField.getText());
            }
            return null;
        });

        dialog.showAndWait();
    }

    public Scene getScene() {
        return new Scene(table);
    }
}
