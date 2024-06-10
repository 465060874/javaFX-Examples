package com.demo.javafxexample.tableview;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class TableColumnHyperlinkExample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TableView<Person> tableView = new TableView<>();

        TableColumn<Person, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

        TableColumn<Person, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));

        // 设置自定义单元格工厂
        emailColumn.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Person, String> call(TableColumn<Person, String> param) {
                return new TableCell<>() {
                    private final Hyperlink hyperlink = new Hyperlink();

                    {
                        hyperlink.setOnAction(event -> {
                            String email = getItem();
                            if (email != null) {
                                System.out.println("Email clicked: " + email);
                                // 在这里处理超链接点击事件，例如打开浏览器或发送邮件
                            }
                        });
                    }

                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setGraphic(null);
                        } else {
                            hyperlink.setText(item);
                            setGraphic(hyperlink);
                        }
                    }
                };
            }
        });

        tableView.getColumns().addAll(nameColumn, emailColumn);

        // 添加示例数据
        tableView.getItems().addAll(
                new Person("John Doe", "john.doe@example.com"),
                new Person("Jane Smith", "jane.smith@example.com")
        );

        VBox vbox = new VBox(tableView);
        Scene scene = new Scene(vbox);

        primaryStage.setScene(scene);
        primaryStage.setTitle("TableColumn Hyperlink Example");
        primaryStage.show();
    }

    public static class Person {
        private final SimpleStringProperty name;
        private final SimpleStringProperty email;

        public Person(String name, String email) {
            this.name = new SimpleStringProperty(name);
            this.email = new SimpleStringProperty(email);
        }

        public String getName() {
            return name.get();
        }

        public String getEmail() {
            return email.get();
        }
    }
}
