package com.demo.javafxexample.tableview;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 为了实现一个复杂的 TableView，其中包含按钮，点击按钮后可以将对应的记录用表单显示出来并进行修改，并且修改完成后还要更新 TableView 的记录，你可以参考以下示例代码：
 *
 * 定义数据模型类。
 * 创建 TableView 并定义列。
 * 创建包含按钮的自定义单元格工厂。
 * 创建表单界面。
 * 将表单与数据模型绑定，并在修改后更新 TableView。
 *
 * 解释
 * Person 类：定义了一个包含姓名、年龄和地址的简单数据模型类。这个类还包含了一些 setter 方法，用于更新属性值。
 *
 * TableView：创建了一个 TableView，并定义了四列来显示 Person 对象的姓名、年龄、地址和操作按钮。
 *
 * 自定义单元格工厂：为操作列定义了一个自定义单元格工厂，其中包含一个 "Edit" 按钮。点击按钮时会调用 showEditForm 方法显示编辑表单。
 *
 * 编辑表单界面：showEditForm 方法创建了一个包含文本字段和保存/取消按钮的对话框。用户可以在这个表单中编辑记录的属性，并通过保存按钮提交修改。
 *
 * 更新 TableView：当用户在表单中修改了记录并点击保存按钮时，更新 Person 对象的属性，并调用 tableView.refresh() 方法刷新 TableView 以显示更新后的数据。
 *
 * 运行这个示例时，表格中的每一行都有一个 "Edit" 按钮，点击按钮会弹出一个编辑表单，允许用户修改对应记录的属性。保存修改后，TableView 会自动更新以显示修改后的数据。
 */
public class TableViewWithFormExample extends Application {

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

        public void setName(String name) {
            this.name.set(name);
        }

        public int getAge() {
            return age.get();
        }

        public void setAge(int age) {
            this.age.set(age);
        }

        public String getAddress() {
            return address.get();
        }

        public void setAddress(String address) {
            this.address.set(address);
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
            private final Button editButton = new Button("Edit");

            {
                editButton.setOnAction(event -> {
                    Person person = getTableView().getItems().get(getIndex());
                    showEditForm(person, getTableView());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(editButton);
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
        primaryStage.setTitle("TableView with Form Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showEditForm(Person person, TableView<Person> tableView) {
        // 创建表单界面
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(5);

        TextField nameField = new TextField(person.getName());
        TextField ageField = new TextField(Integer.toString(person.getAge()));
        TextField addressField = new TextField(person.getAddress());

        form.addRow(0, new Label("Name:"), nameField);
        form.addRow(1, new Label("Age:"), ageField);
        form.addRow(2, new Label("Address:"), addressField);

        // 创建对话框
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setTitle("Edit Person");

        // 创建保存按钮
        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
            person.setName(nameField.getText());
            person.setAge(Integer.parseInt(ageField.getText()));
            person.setAddress(addressField.getText());
            tableView.refresh(); // 更新 TableView
            dialogStage.close();
        });

        // 创建取消按钮
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> dialogStage.close());

        HBox buttonBox = new HBox(10, saveButton, cancelButton);
        buttonBox.setPadding(new Insets(10));

        VBox dialogVBox = new VBox(10, form, buttonBox);
        dialogVBox.setPadding(new Insets(20));

        Scene dialogScene = new Scene(dialogVBox);
        dialogStage.setScene(dialogScene);
        dialogStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
