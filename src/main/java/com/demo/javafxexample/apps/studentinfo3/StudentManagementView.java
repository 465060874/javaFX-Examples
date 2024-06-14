package com.demo.javafxexample.apps.studentinfo3;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;


public class StudentManagementView extends VBox {
    private TableView<Student> tableView;
    private Button editButton;
    private StudentViewModel viewModel;

    public StudentManagementView(StudentViewModel viewModel) {
        this.viewModel = viewModel;
        initializeUI();
        setupBindings();
    }

    private void initializeUI() {
        tableView = new TableView<>();
        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        TableColumn<Student, String> classNameColumn = new TableColumn<>("Class");
        classNameColumn.setCellValueFactory(cellData -> cellData.getValue().classNameProperty());
        TableColumn<Student, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        tableView.getColumns().addAll(nameColumn, classNameColumn, addressColumn);

        editButton = new Button("Edit");
        editButton.setOnAction(event -> showEditDialog());

        getChildren().addAll(tableView, editButton);
    }

    private void setupBindings() {
//        tableView.itemsProperty().bindBidirectional(viewModel.getStudents()).bind(viewModel.getStudents());
    }

    private void showEditDialog() {
        Student selectedStudent = tableView.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            viewModel.setSelectedStudent(selectedStudent);
            // 创建并显示编辑对话框
            StudentEditDialog editDialog = new StudentEditDialog(viewModel);
            editDialog.showAndWait();
        }
    }
}
