package com.demo.javafxexample.text;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TextExample extends Application{

    @Override
    public void start(Stage primaryStage) {
        Text readOnlyText = new Text("This is a read-only text.");

        VBox root = new VBox();
        root.getChildren().add(readOnlyText);

        Scene scene = new Scene(root, 200, 100);

        primaryStage.setTitle("Read-Only Text Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
