package com.demo.javafxexample.tableview;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;

public class IkonliTableCellExample extends Application {
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
                new Person("Jane Smith"),
                new Person("Jack Johnson")
        );

        tableView.getColumns().add(nameColumn);

        Scene scene = new Scene(tableView);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Ikonli TableCell Example");
        primaryStage.show();
    }

    // 自定义 TableCell 类
    private class CopyableTableCell extends TableCell<Person, String> {
        private HBox hbox;
        private Label textLabel;
        private Button copyButton;
        private String content;

        public CopyableTableCell() {
            hbox = new HBox();
            textLabel = new Label();
            textLabel.setMinWidth(150); // 设置最小宽度
            textLabel.setPrefWidth(150); // 设置首选宽度
            textLabel.setMaxWidth(150); // 设置最大宽度

            // 使用 Ikonli 创建包含图标的按钮
            FontIcon copyIcon = new FontIcon(FontAwesomeSolid.COPY);
            copyButton = new Button("", copyIcon);
            // 自定义按钮样式
            copyButton.setStyle("-fx-background-color: transparent; " +
                    "-fx-border-color: transparent; " +
                    "-fx-text-fill: blue; " +
                    "-fx-underline: true;");
            // 添加悬停效果和手形光标
            copyButton.setOnMouseEntered(event -> {
                copyButton.setStyle("-fx-background-color: transparent; " +
                        "-fx-border-color: transparent; " +
                        "-fx-text-fill: darkblue; " + // 悬停时改变文本颜色
                        "-fx-underline: true;");
                copyButton.setCursor(Cursor.HAND); // 设置手形光标
            });

            copyButton.setOnMouseExited(event -> {
                copyButton.setStyle("-fx-background-color: transparent; " +
                        "-fx-border-color: transparent; " +
                        "-fx-text-fill: blue; " +
                        "-fx-underline: true;");
                copyButton.setCursor(Cursor.DEFAULT); // 恢复默认光标
            });

            copyButton.setOnAction(event -> copyToClipboard(content));

            // 填充器使按钮靠右
            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);
            hbox.getChildren().addAll(textLabel, spacer, copyButton);
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                content = item;
                textLabel.setText(item);
                setText(null);
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
