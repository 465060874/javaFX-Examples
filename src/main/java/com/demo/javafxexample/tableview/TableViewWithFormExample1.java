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
 * 要实现在 JavaFX 中构造一个复杂的 TableView，其中包含按钮，点击按钮后可以将对应的记录用表单显示出来并进行修改，你可以按照以下步骤进行：
 *
 * 定义数据模型类：首先，定义一个数据模型类来表示表格中的每一行数据。这个类应该包含需要显示和修改的各种属性。
 *
 * 创建 TableView：使用 TableView 来显示数据。将数据模型类的对象添加到 TableView 中，每个对象对应表格中的一行数据。
 *
 * 创建包含按钮的自定义单元格工厂：为按钮列定义一个自定义单元格工厂，以便在每个单元格中创建一个按钮。按钮的点击事件应该触发显示对应记录的表单的动作。
 *
 * 创建表单界面：当用户点击按钮时，显示一个表单界面来编辑对应记录的属性。可以使用 JavaFX 的各种控件来构建表单，比如 TextField、ComboBox、CheckBox 等。
 *
 * 将表单与数据模型绑定：将表单的控件与数据模型类的属性进行绑定，以便在表单中显示记录的属性，并且在用户修改后更新数据模型类的属性。
 */
public class TableViewWithFormExample1 extends Application {

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
                    showEditForm(person);
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

    private void showEditForm(Person person) {
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
