package com.demo.javafxexample.tableview;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

public class SelectableTableCellExample extends Application {

    public class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Override
    public void start(Stage primaryStage) {
        TableView<Person> tableView = new TableView<>();

        TableColumn<Person, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn()); // 设置单元格为可编辑的TextFieldTableCell

        nameColumn.setOnEditCommit(event -> {
            Person person = event.getRowValue();
            person.setName(event.getNewValue());
        });

        tableView.getColumns().add(nameColumn);

        tableView.getItems().add(new Person("Alice"));
        tableView.getItems().add(new Person("Bob"));
        tableView.getItems().add(new Person("Charlie"));

        tableView.setEditable(true); // 设置表格为可编辑

        Scene scene = new Scene(tableView, 300, 200);

        primaryStage.setTitle("Editable TableCell Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

