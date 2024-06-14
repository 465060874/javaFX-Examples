package com.demo.javafxexample.apps.studentinfo7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class StudentApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        URL location = getClass().getResource("/studentinfo7/student_view.fxml");
        FXMLLoader loader = new FXMLLoader(location);
        loader.setLocation(location);
        Parent root = loader.load();
//        FXMLLoader fxmlLoader = new FXMLLoader( StudentApplication.class.getResource("student_view.fxml") );
//        Parent root1 = FXMLLoader.load(getClass().getResource("student_view.fxml"));
//        Parent root = fxmlLoader.getRoot();
        primaryStage.setTitle("Student Management System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
