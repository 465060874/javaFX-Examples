package com.demo.javafxexample.apps.studentinfo2;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class StudentManagementApp extends Application {

    private TreeView<String> gradeTreeView;
    private TableView<Student> studentTableView;
    private Map<String, ObservableList<Student>> studentData;

    @Override
    public void start(Stage primaryStage) {
        // Initialize the student data
        studentData = new HashMap<>();
        initializeData();

        // Left part: TreeView for grade list
        gradeTreeView = new TreeView<>();
        TreeItem<String> rootItem = new TreeItem<>("Grades");
        rootItem.setExpanded(true);
        gradeTreeView.setRoot(rootItem);

        for (String grade : studentData.keySet()) {
            TreeItem<String> gradeItem = new TreeItem<>(grade);
            rootItem.getChildren().add(gradeItem);
        }

        gradeTreeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                String selectedGrade = newValue.getValue();
                showStudentsForGrade(selectedGrade);
            }
        });

        // Right part: TableView for student list
        studentTableView = new TableView<>();
        TableColumn<Student, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Student, String> genderColumn = new TableColumn<>("Gender");
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

        TableColumn<Student, Void> editColumn = new TableColumn<>("Edit");
        editColumn.setCellFactory(param -> new TableCell<>() {
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

        studentTableView.getColumns().addAll(idColumn, nameColumn, genderColumn, editColumn);

        // Layout setup
        HBox root = new HBox();
        root.setSpacing(10);
        root.getChildren().addAll(new VBox(gradeTreeView), studentTableView);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Student Information Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializeData() {
        ObservableList<Student> grade1Students = FXCollections.observableArrayList(
                new Student("001", "Alice", 10, "Female", "123456789", "Address 1"),
                new Student("002", "Bob", 11, "Male", "987654321", "Address 2")
        );
        studentData.put("Grade 1", grade1Students);

        ObservableList<Student> grade2Students = FXCollections.observableArrayList(
                new Student("003", "Charlie", 12, "Male", "555555555", "Address 3"),
                new Student("004", "Diana", 13, "Female", "666666666", "Address 4")
        );
        studentData.put("Grade 2", grade2Students);
    }

    private void showStudentsForGrade(String grade) {
        ObservableList<Student> students = studentData.get(grade);
        if (students != null) {
            studentTableView.setItems(students);
        } else {
            studentTableView.setItems(FXCollections.emptyObservableList());
        }
    }

    private void showEditDialog(Student student) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Edit Student");

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        TextField idField = new TextField(student.getId());
        TextField nameField = new TextField(student.getName());
        TextField ageField = new TextField(String.valueOf(student.getAge()));
        TextField genderField = new TextField(student.getGender());
        TextField phoneField = new TextField(student.getPhone());
        TextField addressField = new TextField(student.getAddress());

        gridPane.add(new Label("ID:"), 0, 0);
        gridPane.add(idField, 1, 0);
        gridPane.add(new Label("Name:"), 0, 1);
        gridPane.add(nameField, 1, 1);
        gridPane.add(new Label("Age:"), 0, 2);
        gridPane.add(ageField, 1, 2);
        gridPane.add(new Label("Gender:"), 0, 3);
        gridPane.add(genderField, 1, 3);
        gridPane.add(new Label("Phone:"), 0, 4);
        gridPane.add(phoneField, 1, 4);
        gridPane.add(new Label("Address:"), 0, 5);
        gridPane.add(addressField, 1, 5);

        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
            student.setId(idField.getText());
            student.setName(nameField.getText());
            student.setAge(Integer.parseInt(ageField.getText()));
            student.setGender(genderField.getText());
            student.setPhone(phoneField.getText());
            student.setAddress(addressField.getText());
            studentTableView.refresh();
            dialog.close();
        });

        VBox vBox = new VBox(gridPane, saveButton);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));

        Scene scene = new Scene(vBox);
        dialog.setScene(scene);
        dialog.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
