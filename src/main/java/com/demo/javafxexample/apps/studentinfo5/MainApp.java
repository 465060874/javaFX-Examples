package com.demo.javafxexample.apps.studentinfo5;

/**
 * 4. 主应用程序
 * 最后，创建JavaFX应用程序来启动并展示View。
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        StudentViewModel viewModel = new StudentViewModel();
        StudentView view = new StudentView(viewModel);

        Scene scene = new Scene(view.createView(), 600, 400);
        primaryStage.setTitle("Student Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
