package com.demo.javafxexample.apps.richeditor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class WebViewEditor extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // 启动本地服务器处理文件上传请求
        startServer();

        // 加载本地的 editor.html 文件
        webEngine.load(getClass().getResource("/editor/tinymce.html").toExternalForm());

        Scene scene = new Scene(webView, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.setTitle("TinyMCE Rich Text Editor with File Upload");
        primaryStage.show();
    }

    private void startServer() throws Exception {
        Server server = new Server(8080);
        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handler.setContextPath("/");
        server.setHandler(handler);

        // 添加上传处理 Servlet
        handler.addServlet(new ServletHolder(new FileUploadServlet()), "/uploads");

        server.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

