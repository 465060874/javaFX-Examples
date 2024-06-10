package com.demo.javafxexample.apps.richeditor;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.form.FormData;
import io.undertow.server.handlers.form.FormDataParser;
import io.undertow.util.Headers;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class JavaFXUndertowFileUpload extends Application {

    @Override
    public void start(Stage primaryStage) {
        Undertow server = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(new HttpHandler() {
                    @Override
                    public void handleRequest(HttpServerExchange exchange) throws Exception {
                        if (exchange.getRequestPath().equals("/upload")) {
                            exchange.getRequestReceiver().receiveFullString((ex, message) -> {
                                System.out.println("Received file: " + message);
                                exchange.getResponseSender().send("File uploaded successfully!");
                                // 处理文件上传逻辑，将文件保存到指定目录
                            });
                        } else {
                            exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                            exchange.getResponseSender().send("Hello, Undertow!");
                        }
                    }
                })
                .build();
        server.start();

        WebView webView = new WebView();
//        webView.getEngine().load("http://localhost:8080/editor.html");
        webView.getEngine().load(getClass().getResource("/editor/tinymce.html").toExternalForm());

        Scene scene = new Scene(webView, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest((event) -> {
            System.out.println("Closing Stage");
            server.stop();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
