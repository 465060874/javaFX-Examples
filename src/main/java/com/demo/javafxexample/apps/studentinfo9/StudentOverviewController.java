package com.demo.javafxexample.apps.studentinfo9;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StudentOverviewController {
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, String> nameColumn;
    @FXML
    private TableColumn<Student, String> classColumn;
    @FXML
    private TableColumn<Student, String> addressColumn;

    @FXML
    private TableColumn<Student, Void> editColumn; // Change the type to Void since we're not binding to a property

    private StudentViewModel viewModel;

    @FXML
    private void initialize() {
        viewModel = new StudentViewModel();
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        classColumn.setCellValueFactory(new PropertyValueFactory<>("className"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        studentTable.setItems(viewModel.getStudents());
        // Set cell factory for the edit button column
        editColumn.setCellFactory(param -> new TableCell<Student, Void>() {
            private final Button btn = new Button("Edit");

            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    btn.setOnAction(event -> {
                        Student selectedStudent = getTableView().getItems().get(getIndex());
                        editStudent(selectedStudent);
                    });
                    setGraphic(btn);
                }
            }
        });
    }

    private void editStudent(Student student) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/studentinfo9/StudentEditDialog.fxml"));
            Parent root = loader.load();
            StudentEditDialogController controller = loader.getController();
            controller.setStudent(student);
            controller.setViewModel(viewModel);

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Student");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(studentTable.getScene().getWindow());
            dialogStage.setScene(new Scene(root));
            dialogStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleEditStudent() {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/studentinfo9/StudentEditDialog.fxml"));
                Parent root = loader.load();
                StudentEditDialogController controller = loader.getController();
                controller.setStudent(selectedStudent);
                controller.setViewModel(viewModel);

                Stage dialogStage = new Stage();
                dialogStage.setTitle("Edit Student");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(studentTable.getScene().getWindow());
                dialogStage.setScene(new Scene(root));
                dialogStage.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // No student selected
        }
    }
}

