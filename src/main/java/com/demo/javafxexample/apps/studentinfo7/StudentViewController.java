package com.demo.javafxexample.apps.studentinfo7;

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
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;

public class StudentViewController {
    @FXML
    private TableView<Student> table;
    @FXML
    private TableColumn<Student, String> nameColumn;
    @FXML
    private TableColumn<Student, String> classColumn;
    @FXML
    private TableColumn<Student, String> addressColumn;
    @FXML
    private TableColumn<Student, Void> editColumn;

    private StudentViewModel viewModel;

    public void initialize() {
        viewModel = new StudentViewModel();
        table.setItems(viewModel.getStudents());

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        classColumn.setCellValueFactory(new PropertyValueFactory<>("className"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        editColumn.setCellFactory(getEditCellFactory());
    }

    private Callback<TableColumn<Student, Void>, TableCell<Student, Void>> getEditCellFactory() {
        return param -> new TableCell<>() {
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
        };
    }

    private void showEditDialog(Student student) {
        try {
            URL location = getClass().getResource("/studentinfo7/student_edit_dialog.fxml");
            FXMLLoader loader = new FXMLLoader(location);
            loader.setLocation(location);
            Parent parent = loader.load();

            StudentEditDialogController controller = loader.getController();
            controller.setStudent(student);
            controller.setViewModel(viewModel);

            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(new Scene(parent));
            dialogStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
