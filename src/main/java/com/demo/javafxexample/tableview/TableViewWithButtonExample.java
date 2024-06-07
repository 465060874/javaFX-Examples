package com.demo.javafxexample.tableview;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 在 JavaFX 中，为 TableView 创建包含按钮的复杂单元格，并在点击按钮时弹出对话框显示对应的记录信息，可以通过自定义单元格工厂实现。下面是一个完整的示例代码，演示如何实现这一功能：
 *
 * 定义数据模型类。
 * 创建 TableView 并定义列。
 * 创建包含按钮的自定义单元格工厂。
 * 设置按钮的点击事件以显示对话框。
 */

public class TableViewWithButtonExample extends Application {

    public static class Person {
        private final SimpleStringProperty name;
        private final SimpleIntegerProperty age;
        private final SimpleStringProperty address;

        public Person(String name, int age, String address) {
            this.name = new SimpleStringProperty(name);
            this.age = new SimpleIntegerProperty(age);
            this.address = new SimpleStringProperty(address);
        }

        public String getName() {
            return name.get();
        }

        public int getAge() {
            return age.get();
        }

        public String getAddress() {
            return address.get();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        // 创建 TableView
        TableView<Person> tableView = new TableView<>();

        // 创建列
        TableColumn<Person, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Person, Integer> ageColumn = new TableColumn<>("Age");
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<Person, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn<Person, Void> actionColumn = new TableColumn<>("Action");
        actionColumn.setCellFactory(param -> new TableCell<>() {
            private final Button button = new Button("Details");

            {
                button.setOnAction(event -> {
                    Person person = getTableView().getItems().get(getIndex());
                    showDetailsDialog(person);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(button);
                }
            }
        });

        // 添加列到 TableView
        tableView.getColumns().addAll(nameColumn, ageColumn, addressColumn, actionColumn);

        // 添加数据到 TableView
        tableView.getItems().addAll(
                new Person("Alice", 30, "1234 Main St"),
                new Person("Bob", 25, "5678 Oak St"),
                new Person("Charlie", 35, "9101 Pine St")
        );

        // 创建根布局
        StackPane root = new StackPane(tableView);
        Scene scene = new Scene(root, 600, 400);

        // 设置舞台
        primaryStage.setTitle("TableView with Button Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showDetailsDialog(Person person) {
        // 创建对话框
        Dialog<Void> dialog = new Dialog<>();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Person Details");

        // 设置对话框内容
        Label nameLabel = new Label("Name: " + person.getName());
        Label ageLabel = new Label("Age: " + person.getAge());
        Label addressLabel = new Label("Address: " + person.getAddress());

        // 布局
        VBox vbox = new VBox(nameLabel, ageLabel, addressLabel);
        vbox.setSpacing(10);
        dialog.getDialogPane().setContent(vbox);

        // 添加一个按钮关闭对话框
        ButtonType closeButtonType = new ButtonType("Close", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(closeButtonType);

        // 显示对话框并等待用户关闭
        dialog.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
