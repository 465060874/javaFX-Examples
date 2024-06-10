package com.demo.javafxexample.htmleditor;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public class WebViewEditor extends Application {

    @Override
    public void start(Stage primaryStage) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // 加载本地的 editor.html 文件
        webEngine.load(getClass().getResource("/editor/editor.html").toExternalForm());

        // 设置 JavaScript 与 Java 之间的桥梁
        webEngine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            if (newState == javafx.concurrent.Worker.State.SUCCEEDED) {
                JSObject window = (JSObject) webEngine.executeScript("window");
                window.setMember("javaConnector", new JavaConnector());
            }
        });

        Scene scene = new Scene(webView, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Rich Text Editor");
        primaryStage.show();
    }

    // 定义一个类作为 Java 和 JavaScript 之间的接口
    public class JavaConnector {
        public void getContent(String content) {
            System.out.println("Editor content: " + content);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

