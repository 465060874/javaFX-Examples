package com.demo.javafxexample.apps.studentinfo1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

public class StudentInfoManager extends Application {

    private TreeView<String> gradeTreeView;
    private TableView<Student> studentTableView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("学生信息管理系统");

        // 创建年级列表
        TreeItem<String> rootItem = new TreeItem<>("年级");
        rootItem.setExpanded(true);
        String[] grades = {"一年级", "二年级", "三年级"};
        for (String grade : grades) {
            TreeItem<String> gradeItem = new TreeItem<>(grade);
            rootItem.getChildren().add(gradeItem);
        }

        gradeTreeView = new TreeView<>(rootItem);
        gradeTreeView.getStyleClass().add(BootstrapFX.bootstrapFXStylesheet());
        gradeTreeView.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            if (newValue != null) {
                showStudents(newValue.getValue());
            }
        });

        // 创建学生列表
        studentTableView = new TableView<>();
        studentTableView.getStyleClass().add(BootstrapFX.bootstrapFXStylesheet());
        TableColumn<Student, String> idColumn = new TableColumn<>("学号");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());

        TableColumn<Student, String> nameColumn = new TableColumn<>("姓名");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        TableColumn<Student, String> genderColumn = new TableColumn<>("性别");
        genderColumn.setCellValueFactory(cellData -> cellData.getValue().genderProperty());

        TableColumn<Student, Void> editColumn = new TableColumn<>("操作");
        editColumn.setCellFactory(param -> new TableCell<>() {
            private final Button editButton = new Button("编辑");

            {
                editButton.getStyleClass().addAll("btn", "btn-primary");
                editButton.setOnAction(event -> {
                    Student student = getTableView().getItems().get(getIndex());
                    showEditDialog(student);
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

        studentTableView.getColumns().addAll(idColumn, nameColumn, genderColumn, editColumn);

        // 布局
        BorderPane root = new BorderPane();
        root.setLeft(gradeTreeView);
        root.setCenter(studentTableView);

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showStudents(String grade) {
        // 根据年级显示学生列表，这里仅作为示例，实际数据可以从数据库或其他数据源获取
        studentTableView.getItems().clear();
        if (grade.equals("一年级")) {
            studentTableView.getItems().addAll(
                    new Student("001", "张三", "男"),
                    new Student("002", "李四", "女")
            );
        } else if (grade.equals("二年级")) {
            studentTableView.getItems().addAll(
                    new Student("003", "王五", "男"),
                    new Student("004", "赵六", "女")
            );
        }
    }

    private void showEditDialog(Student student) {
        // 创建编辑对话框
        Dialog<Student> dialog = new Dialog<>();
        dialog.setTitle("编辑学生信息");

        // 设置按钮
        ButtonType okButtonType = new ButtonType("确定", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

        // 创建表单
        TextField idField = new TextField(student.getId());
        TextField nameField = new TextField(student.getName());
        TextField genderField = new TextField(student.getGender());
        idField.getStyleClass().addAll("form-control");
        nameField.getStyleClass().addAll("form-control");
        genderField.getStyleClass().addAll("form-control");

        VBox form = new VBox();
        form.getChildren().addAll(new Label("学号:"), idField, new Label("姓名:"), nameField, new Label("性别:"), genderField);
        form.getStyleClass().addAll("form-group");
        dialog.getDialogPane().setContent(form);

        // 结果转换
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                return new Student(idField.getText(), nameField.getText(), genderField.getText());
            }
            return null;
        });

        dialog.showAndWait().ifPresent(result -> {
            student.setId(result.getId());
            student.setName(result.getName());
            student.setGender(result.getGender());
            studentTableView.refresh();
        });
    }
}
