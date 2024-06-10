package com.demo.javafxexample.text;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SelectableTextFieldExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        TextField selectableTextField = new TextField("This is a selectable and readable text");
        selectableTextField.setEditable(false); // 设置为只读
        selectableTextField.setStyle("-fx-border-width: 0; -fx-background-color: transparent; -fx-background-insets: 0;");

        // 确保文本全选时可以看到所有的文本
        selectableTextField.setPrefColumnCount(selectableTextField.getText().length());
        selectableTextField.positionCaret(selectableTextField.getText().length()); // 设置光标位置到文本末尾
        selectableTextField.requestFocus(); // 设置焦点到文本框

        StackPane root = new StackPane();
        root.getChildren().add(selectableTextField);

        Scene scene = new Scene(root, 300, 100);

        primaryStage.setTitle("Selectable Text Field Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
