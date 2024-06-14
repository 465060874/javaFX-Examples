package com.demo.javafxexample.apps.studentinfo5;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * 3. 创建View
 * 现在我们来创建View，其中包括一个TableView来显示学生数据，以及每行一个按钮来编辑信息。
 */
public class StudentView {
    private final StudentViewModel viewModel;

    public StudentView(StudentViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public Parent createView() {
        TableView<Student> tableView = new TableView<>();
        tableView.setItems(viewModel.getStudents());

        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        TableColumn<Student, String> classColumn = new TableColumn<>("Class");
        classColumn.setCellValueFactory(cellData -> cellData.getValue().classNameProperty());

        TableColumn<Student, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());

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
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(editButton);
                }
            }
        });

        tableView.getColumns().addAll(nameColumn, classColumn, addressColumn, actionColumn);
        return new VBox(tableView);
    }

    private void showEditDialog(Student student) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Edit Student");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField nameField = new TextField(student.getName());
        TextField classField = new TextField(student.getClassName());
        TextField addressField = new TextField(student.getAddress());

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Class:"), 0, 1);
        grid.add(classField, 1, 1);
        grid.add(new Label("Address:"), 0, 2);
        grid.add(addressField, 1, 2);

        dialog.getDialogPane().setContent(grid);

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                viewModel.updateStudent(student, nameField.getText(), classField.getText(), addressField.getText());
            }
            return null;
        });

        dialog.showAndWait();
    }
}
