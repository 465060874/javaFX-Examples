package com.demo.javafxexample.tableview;

/**
 * 在JavaFX中，你可以创建一个复杂的表格（TableView），并且可以通过选择记录来弹出详细信息。以下是实现这个功能的一般步骤：
 * <p>
 * 定义数据模型类：首先，你需要定义一个数据模型类来表示表格中的每一行数据。这个类可以包含你想要显示的各种属性。
 * <p>
 * 创建TableView：使用TableView来显示数据。你可以将数据模型类的对象添加到TableView中，每个对象将对应表格中的一行数据。
 * <p>
 * 定义单元格工厂：为了显示复杂的数据或自定义布局，你可能需要定义自己的单元格工厂。这个工厂负责根据数据模型创建每个单元格的视图。
 * <p>
 * 设置行选择监听器：为了在选择行时触发弹出详细信息的动作，你需要添加一个行选择监听器。当用户选择了表格中的一行时，监听器将响应该事件并执行相应的操作。
 * <p>
 * 弹出详细信息：当用户选择了一行并触发了行选择事件时，你可以根据选择的数据显示详细信息，比如使用弹出窗口或其他布局方式。
 */

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TableViewDialogExample extends Application {

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
        // 创建TableView
        TableView<Person> tableView = new TableView<>();

        // 创建列
        TableColumn<Person, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Person, Integer> ageColumn = new TableColumn<>("Age");
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<Person, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        // 添加列到TableView
        tableView.getColumns().addAll(nameColumn, ageColumn, addressColumn);

        // 添加数据到TableView
        tableView.getItems().addAll(
                new Person("Alice", 30, "1234 Main St"),
                new Person("Bob", 25, "5678 Oak St"),
                new Person("Charlie", 35, "9101 Pine St")
        );

        // 设置行选择监听器
        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // 显示详细信息对话框
                showDetailsDialog(newValue);
            }
        });

        // 创建根布局
        StackPane root = new StackPane(tableView);
        Scene scene = new Scene(root, 600, 400);

        // 设置舞台
        primaryStage.setTitle("TableView with Dialog Example");
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
