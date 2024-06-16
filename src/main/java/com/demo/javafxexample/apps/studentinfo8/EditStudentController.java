package com.demo.javafxexample.apps.studentinfo8;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class EditStudentController extends VBox {
    @FXML
    private TextField nameField;
    @FXML
    private TextField classField;
    @FXML
    private TextField addressField;
    @FXML
    private Button saveButton;

    private EditStudentViewModel viewModel;

    private StudentListViewModel listViewModel;

//    public Parent getView() throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/studentinfo8/EditStudentView.fxml"));
//        loader.setRoot(this);
//        loader.setController(this);
//        loader.load();
//        return this;
//    }

    public EditStudentController(StudentListViewModel listViewModel,EditStudentViewModel viewModel) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/studentinfo8/EditStudentView.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        this.viewModel = viewModel;

        nameField.textProperty().bindBidirectional(viewModel.nameProperty());
        classField.textProperty().bindBidirectional(viewModel.classNameProperty());
        addressField.textProperty().bindBidirectional(viewModel.addressProperty());
    }

    @FXML
    void onSaveClick() {
        viewModel.setName(nameField.getText());
        viewModel.setClassName(classField.getText());
        viewModel.setAddress(addressField.getText());
        viewModel.updateStudent(nameField.getText(),classField.getText(),addressField.getText());
        // Close the stage
        ((Stage) saveButton.getScene().getWindow()).close();
    }
}
