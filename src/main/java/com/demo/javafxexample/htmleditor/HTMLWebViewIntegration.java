package com.demo.javafxexample.htmleditor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class HTMLWebViewIntegration extends Application {

    @Override
    public void start(Stage primaryStage) {
        WebView webView = new WebView();
        webView.getEngine().load("https://www.tiny.cloud/docs/demo/full-featured/");

        Scene scene = new Scene(webView, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.setTitle("TinyMCE Integration");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

