package com.demo.javafxexample.webview;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.util.Optional;

public class WebViewJavaDialog extends Application {

    @Override
    public void start(Stage primaryStage) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // 设置自定义的JavaScript对话框处理程序
        webEngine.setOnAlert(event -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("JavaScript Alert");
            alert.setHeaderText(null);
            alert.setContentText(event.getData());
            alert.showAndWait();
        });




        webEngine.setConfirmHandler(message -> {
            Alert confirm = new Alert(AlertType.CONFIRMATION);
            confirm.setTitle("JavaScript Confirm");
            confirm.setHeaderText(null);
            confirm.setContentText(message);

            Optional<ButtonType> result = confirm.showAndWait();
            return result.orElse(ButtonType.CANCEL) == ButtonType.OK;
        });

        webEngine.setPromptHandler(promptData -> {
            TextInputDialog prompt = new TextInputDialog(promptData.getDefaultValue());
            prompt.setTitle("JavaScript Prompt");
            prompt.setHeaderText(null);
            prompt.setContentText(promptData.getMessage());

            Optional<String> result = prompt.showAndWait();
            return result.orElse(null);
        });

        // 加载本地的 index.html 文件
        webEngine.load(getClass().getResource("/html/alert.html").toExternalForm());

        Scene scene = new Scene(webView, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX and JavaScript Integration");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

