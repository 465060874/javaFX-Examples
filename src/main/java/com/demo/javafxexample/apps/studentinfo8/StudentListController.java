package com.demo.javafxexample.apps.studentinfo8;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;

public class StudentListController extends BorderPane {
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, String> nameColumn;
    @FXML
    private TableColumn<Student, String> classColumn;
    @FXML
    private TableColumn<Student, String> addressColumn;
    @FXML
    private TableColumn<Student, Void> actionColumn;

    private StudentListViewModel viewModel;

    public StudentListController(StudentListViewModel viewModel) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/studentinfo8/StudentListView.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        this.viewModel = viewModel;

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        classColumn.setCellValueFactory(cellData -> cellData.getValue().classNameProperty());
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());

        actionColumn.setCellFactory(column -> new TableCell<Student, Void>() {
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    Button editButton = new Button("Edit");
                    editButton.setOnAction(event -> {
                        try {
                            Stage editStage = new Stage();
                            EditStudentController controller = new EditStudentController(viewModel,new EditStudentViewModel(getTableView().getItems().get(getIndex())));
                            editStage.setScene(new Scene(controller));
                            editStage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    setGraphic(editButton);
                }
            }
        });

        studentTable.setItems(viewModel.getStudents());
    }
}
