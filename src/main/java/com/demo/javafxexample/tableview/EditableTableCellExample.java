package com.demo.javafxexample.tableview;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class EditableTableCellExample extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // 创建数据模型
        TableView<Person> tableView = new TableView<>();
        TableColumn<Person, String> nameColumn = new TableColumn<>("Name");

        // 设置列宽
        nameColumn.setPrefWidth(150);

        // 设置可编辑属性
        tableView.setEditable(true);
        nameColumn.setEditable(true);

        // 设置自定义单元格工厂
        nameColumn.setCellFactory(new Callback<TableColumn<Person, String>, TableCell<Person, String>>() {
            @Override
            public TableCell<Person, String> call(TableColumn<Person, String> param) {
                return new EditableTextCell();
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

        VBox vbox = new VBox(tableView);
        Scene scene = new Scene(vbox);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Editable TableCell Example");
        primaryStage.show();
    }

    // 自定义 TableCell 类
    private class EditableTextCell extends TableCell<Person, String> {
        private TextField textField;

        public EditableTextCell() {
            textField = new TextField();
            textField.setEditable(false);
            textField.setOnAction(e -> commitEdit(textField.getText()));
            textField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
                if (!isNowFocused) {
                    commitEdit(textField.getText());
                }
            });
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    textField.setText(item);
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(item);
                    setGraphic(null);
                }
            }
        }

        @Override
        public void startEdit() {
            super.startEdit();
            textField.setText(getItem());
            setText(null);
            setGraphic(textField);
            textField.selectAll();
            textField.requestFocus();
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
            setText(getItem());
            setGraphic(null);
        }

        @Override
        public void commitEdit(String newValue) {
            super.commitEdit(newValue);
            ((Person) getTableRow().getItem()).setName(newValue);
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
