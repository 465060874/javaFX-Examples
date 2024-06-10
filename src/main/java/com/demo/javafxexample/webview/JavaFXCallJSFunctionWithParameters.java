package com.demo.javafxexample.webview;

import javafx.application.Application;
import javafx.concurrent.Worker.State;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class JavaFXCallJSFunctionWithParameters extends Application {

    @Override
    public void start(Stage primaryStage) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // 加载包含JavaScript的网页
        webEngine.loadContent(
                "<html><body>"
                        + "<h1>JavaFX and JavaScript Communication</h1>"
                        + "<script type='text/javascript'>"
                        + "function greet(name) {"
                        + "    alert('Hello, ' + name + ' from JavaScript');"
                        + "}"
                        + "</script>"
                        + "</body></html>");

        // 创建按钮
        Button callJSButton = new Button("Call JavaScript Function");
        callJSButton.setOnAction(event -> {
            String name = "World";
            webEngine.executeScript("greet('" + name + "')");
        });

        // 创建布局
        VBox root = new VBox();
        root.getChildren().addAll(webView, callJSButton);

        // 创建场景并显示
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
