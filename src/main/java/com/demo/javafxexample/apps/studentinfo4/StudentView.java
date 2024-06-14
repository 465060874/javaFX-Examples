package com.demo.javafxexample.apps.studentinfo4;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

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

        TableColumn<Student, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());

        TableColumn<Student, Void> actionColumn = new TableColumn<>("Action");
        actionColumn.setCellFactory(param -> new TableCell<>() {
            private final Button detailsButton = new Button("Details");

            {
                detailsButton.setOnAction(event -> {
                    Student student = getTableView().getItems().get(getIndex());
                    viewModel.showStudentDetails(student);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(detailsButton);
                }
            }
        });

        tableView.getColumns().addAll(nameColumn, addressColumn, actionColumn);
        return new VBox(tableView);
    }
}
