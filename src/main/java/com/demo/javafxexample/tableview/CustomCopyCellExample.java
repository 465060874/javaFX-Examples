package com.demo.javafxexample.tableview;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class CustomCopyCellExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        TableView<String> tableView = new TableView<>();
        TableColumn<String, String> column = new TableColumn<>("Content");
//        column.setCellValueFactory(data -> data.getValue().);
        column.setCellFactory(param -> new CustomCopyCell());

        tableView.getColumns().add(column);
        tableView.getItems().addAll("Text to copy 1", "Text to copy 2");

        Scene scene = new Scene(tableView, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Custom Copy Cell Example");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static class CustomCopyCell extends TableCell<String, String> {

        private final Button copyButton = new Button();
        private final HBox hbox = new HBox();

        public CustomCopyCell() {
            copyButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("copy_icon.png"))));
            copyButton.setOnAction(event -> {
                String content = getItem();
                final Clipboard clipboard = Clipboard.getSystemClipboard();
                final ClipboardContent clipboardContent = new ClipboardContent();
                clipboardContent.putString(content);
                clipboard.setContent(clipboardContent);
            });

            hbox.getChildren().addAll(new Label(), copyButton);
            setGraphic(hbox);
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setGraphic(null);
            } else {
                ((Label)hbox.getChildren().get(0)).setText(item);
                setGraphic(hbox);
            }
        }
    }
}

