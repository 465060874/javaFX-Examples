package com.demo.javafxexample.tableview;

import atlantafx.base.theme.Styles;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.material2.Material2MZ;
import org.kordamp.ikonli.material2.Material2RoundAL;

public class CopyTableCellExample extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // 创建数据模型
        TableView<Person> tableView = new TableView<>();
        TableColumn<Person, String> nameColumn = new TableColumn<>("Name");

        // 设置列宽
        nameColumn.setPrefWidth(200);

        // 设置自定义单元格工厂
        nameColumn.setCellFactory(new Callback<TableColumn<Person, String>, TableCell<Person, String>>() {
            @Override
            public TableCell<Person, String> call(TableColumn<Person, String> param) {
                return new CopyableTableCell();
            }
        });

        // 设置单元格值工厂
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        // 添加数据
        tableView.getItems().addAll(
                new Person("John Doe"),
                new Person("Jane Smith")
        );

        tableView.getColumns().add(nameColumn);

        Scene scene = new Scene(tableView);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Copyable TableCell Example");
        primaryStage.show();
    }

    // 自定义 TableCell 类
    private class CopyableTableCell extends TableCell<Person, String> {
        private final Button copyButton = new Button(null, new FontIcon(Material2RoundAL.CONTENT_COPY));

        //https://kordamp.org/ikonli/cheat-sheet-material2.html
        private final FontIcon accentIcon = new FontIcon(Material2RoundAL.CONTENT_COPY);
        private final HBox hbox = new HBox();
        private Label textLabel;

        public CopyableTableCell() {
//            accentIcon.setOnMouseClicked(event -> {
//                String content = getItem();
//                final Clipboard clipboard = Clipboard.getSystemClipboard();
//                final ClipboardContent clipboardContent = new ClipboardContent();
//                clipboardContent.putString(content);
//                clipboard.setContent(clipboardContent);
//            });
            copyButton.getStyleClass().addAll(Styles.BUTTON_ICON);
//            ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/icons/copy.png")));
//            imageView.setFitWidth(22);
//            imageView.setFitHeight(22);
//            imageView.setPreserveRatio(true);
//            copyButton.setGraphic(accentIcon);
            copyButton.setOnAction(event -> {
                String content = getItem();
                final Clipboard clipboard = Clipboard.getSystemClipboard();
                final ClipboardContent clipboardContent = new ClipboardContent();
                clipboardContent.putString(content);
                clipboard.setContent(clipboardContent);
            });
            textLabel = new Label();
            textLabel.setMinWidth(200); // 设置最小宽度
            textLabel.setPrefWidth(200); // 设置首选宽度
            copyButton.setMinWidth(16);
            copyButton.setMaxWidth(16);

            hbox.getChildren().addAll(textLabel, copyButton);
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

        private void copyToClipboard(String text) {
            final Clipboard clipboard = Clipboard.getSystemClipboard();
            final ClipboardContent content = new ClipboardContent();
            content.putString(text);
            clipboard.setContent(content);
        }
    }

    // 数据模型类
    public class Person {
        private final StringProperty name;

        public Person(String name) {
            this.name = new SimpleStringProperty(name);
        }

        public StringProperty nameProperty() {
            return name;
        }

        public void setName(String name) {
            this.name.set(name);
        }
    }
}
