package com.demo.javafxexample.webview;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public class WebViewJavaConnector extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox vBox = new VBox();;
        Button button = new Button("call javascript function");
        Button button1 = new Button("pass parameter to js function");
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // 加载本地的 index.html 文件
        webEngine.load(getClass().getResource("/html/webview.html").toExternalForm());

        // 在页面加载完成后，将Java对象暴露给JavaScript
        webEngine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            if (newState == javafx.concurrent.Worker.State.SUCCEEDED) {
                JSObject window = (JSObject) webEngine.executeScript("window");
                window.setMember("javaConnector", new JavaConnector());
                button1.setOnAction(event -> {
                    String name = "World";
                    webEngine.executeScript("jsFunctionGreeting('" + name + "')");
                });

                button.setOnAction(e -> {
                    Object result = webEngine.executeScript("myJavaScriptFunction()");
                    javafx.application.Platform.runLater(() -> {
                        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
                        alert.setTitle("call JavaScript function");
                        alert.setContentText(result.toString());
                        alert.showAndWait();
                    });
                    System.out.println("JavaScript function returned: " + result);
                });
            }
        });



        vBox.getChildren().addAll(webView,button, button1);
        Scene scene = new Scene(vBox, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX and JavaScript Integration");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    // 定义一个类作为Java和JavaScript之间的接口
    public class JavaConnector {
        public void showMessage(String message) {
            System.out.println("Message from JavaScript: " + message);
        }

        public void showAlert(String message) {
            javafx.application.Platform.runLater(() -> {
                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
                alert.setTitle("Alert from JavaScript");
                alert.setContentText(message);
                alert.showAndWait();
            });
        }
    }
}

