package com.demo.javafxexample.htmleditor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class TinyMCEEditorExample extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // Load the TinyMCE editor HTML
        String editorHtml = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <script src=\"https://cdn.tiny.cloud/1/no-api-key/tinymce/5/tinymce.min.js\" referrerpolicy=\"origin\"></script>\n" +
                "  <script>\n" +
                "    tinymce.init({\n" +
                "      selector: '#editor',\n" +
                "      height: 500,\n" +
                "      plugins: [\n" +
                "        'advlist autolink lists link image charmap print preview anchor',\n" +
                "        'searchreplace visualblocks code fullscreen',\n" +
                "        'insertdatetime media table paste code help wordcount'\n" +
                "      ],\n" +
                "      toolbar: 'undo redo | formatselect | bold italic backcolor |\n" +
                "      alignleft aligncenter alignright alignjustify | \n" +
                "      bullist numlist outdent indent | removeformat | help'\n" +
                "    });\n" +
                "  </script>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <textarea id=\"editor\">This is a TinyMCE editor integrated into JavaFX</textarea>\n" +
                "</body>\n" +
                "</html>";

        webEngine.loadContent(editorHtml);

        Scene scene = new Scene(webView, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TinyMCE Editor Example");
        primaryStage.show();
    }
}
