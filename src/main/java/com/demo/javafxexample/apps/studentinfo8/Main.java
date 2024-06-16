package com.demo.javafxexample.apps.studentinfo8;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        StudentListViewModel viewModel = new StudentListViewModel();
        StudentListController controller = new StudentListController(viewModel);
        Scene scene = new Scene(controller);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Student Management System");
        primaryStage.show();
    }
}
