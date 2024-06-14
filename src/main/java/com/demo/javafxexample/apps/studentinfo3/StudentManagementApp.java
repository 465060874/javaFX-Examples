package com.demo.javafxexample.apps.studentinfo3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StudentManagementApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        StudentViewModel viewModel = new StudentViewModel();
        // 添加一些示例数据
        viewModel.getStudents().addAll(
                new Student("John", "Class A", "Address 1"),
                new Student("Jane", "Class B", "Address 2"),
                new Student("Mike", "Class C", "Address 3")
        );

        StudentManagementView view = new StudentManagementView(viewModel);
        Scene scene = new Scene(view, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Student Management System");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
