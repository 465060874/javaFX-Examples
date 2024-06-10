package com.demo.javafxexample.tableview;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class TableViewWithTextField extends Application {
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

        // 设置单元格内容工厂
//        nameColumn.setCellFactory(column -> new TextFieldTableCell<>(new StringConverter()));

        // 设置单元格值工厂
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

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
        primaryStage.setTitle("TableView with TextField");
        primaryStage.show();
    }

    // 自定义 StringConverter
    private class StringConverter extends javafx.util.StringConverter<String> {
        @Override
        public String toString(String object) {
            return object;
        }

        @Override
        public String fromString(String string) {
            return string;
        }
    }

    // 数据模型类
    public class Person {
        private final SimpleStringProperty name;

        public Person(String name) {
            this.name = new SimpleStringProperty(name);
        }

        public SimpleStringProperty nameProperty() {
            return name;
        }
    }
}
