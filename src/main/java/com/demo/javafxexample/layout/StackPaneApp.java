package com.demo.javafxexample.layout;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class StackPaneApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        StackPane pane = new StackPane(
                new Rectangle(200, 100, Color.BLACK),
                new Circle(40, Color.RED),
                new Button("Hello StackPane")
        );

        stage.setScene(new Scene(pane, 300, 300));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
