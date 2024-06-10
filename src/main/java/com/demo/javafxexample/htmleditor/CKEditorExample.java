package com.demo.javafxexample.htmleditor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CKEditorExample extends Application {
    WebView webView;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // Load the CKEditor HTML
        String editorHtml = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <script src=\"https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/ckeditor.js\"></script>\n" +
                "  <script>\n" +
                "    document.addEventListener('DOMContentLoaded', function() {\n" +
                "      ClassicEditor.create(document.querySelector('#editor'), {\n" +
                "        ckfinder: {\n" +
                "          uploadUrl: 'http://localhost:8080/upload'\n" + // Set the correct upload URL
                "        }\n" +
                "      }).then(editor => {\n" +
                "        window.editor = editor;\n" +
                "      }).catch(error => {\n" +
                "        console.error(error);\n" +
                "      });\n" +
                "    });\n" +
                "  </script>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <textarea id=\"editor\">This is CKEditor integrated into JavaFX</textarea>\n" +
                "</body>\n" +
                "</html>";

        webEngine.loadContent(editorHtml);

        // Button to simulate image upload
        Button uploadButton = new Button("Upload Image");
        uploadButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null) {
                uploadImage(selectedFile);
            }
        });

        VBox vbox = new VBox(uploadButton, webView);
        Scene scene = new Scene(vbox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("CKEditor Example");
        primaryStage.show();
    }

    private void uploadImage(File file) {
        try {
            Path destination = Paths.get("/uploads/" + file.getName()); // Adjust the upload directory
            Files.copy(file.toPath(), destination);

            String imageUrl = "http://localhost:8080/uploads/" + file.getName(); // Adjust the URL to match your server
            String script = "window.editor.model.change(writer => {" +
                    "const imageElement = writer.createElement('image', {" +
                    "src: '" + imageUrl + "'" +
                    "});" +
                    "window.editor.model.insertContent(imageElement, window.editor.model.document.selection);" +
                    "});";
            webView.getEngine().executeScript(script);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
