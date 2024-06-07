package com.demo.javafxexample.treeview;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;

public class CustomTreeViewWithIconsExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 创建自定义对象类
        class CustomObject {
            private String name;
            private String description;
            private List<CustomObject> children;

            public CustomObject(String name, String description) {
                this.name = name;
                this.description = description;
                this.children = new ArrayList<>();
            }

            public String getName() {
                return name;
            }

            public String getDescription() {
                return description;
            }

            public List<CustomObject> getChildren() {
                return children;
            }

            public void addChild(CustomObject child) {
                children.add(child);
            }
        }

        // 创建TreeItem的扩展类
        class CustomTreeItem extends TreeItem<CustomObject> {
            private boolean isFirstTimeChildren = true;

            public CustomTreeItem(CustomObject customObject) {
                super(customObject);
            }

            @Override
            public boolean isLeaf() {
                return getValue().getChildren().isEmpty();
            }

            @Override
            public ObservableList<TreeItem<CustomObject>> getChildren() {
                if (isFirstTimeChildren) {
                    isFirstTimeChildren = false;
                    List<CustomObject> childrenObjects = getValue().getChildren();
                    if (childrenObjects != null) {
                        for (CustomObject childObject : childrenObjects) {
                            super.getChildren().add(new CustomTreeItem(childObject));
                        }
                    }
                }
                return super.getChildren();
            }
        }

        // 创建自定义对象并构建树形结构
        CustomObject rootObject = new CustomObject("Root", "Root Description");
        CustomObject child1 = new CustomObject("Child 1", "Child 1 Description");
        CustomObject child2 = new CustomObject("Child 2", "Child 2 Description");
        CustomObject child3 = new CustomObject("Child 3", "Child 3 Description");
        rootObject.addChild(child1);
        rootObject.addChild(child2);
        rootObject.addChild(child3);

        CustomTreeItem rootItem = new CustomTreeItem(rootObject);

        // 创建TreeView并绑定根节点
        TreeView<CustomObject> treeView = new TreeView<>(rootItem);

        // 自定义TreeCell以显示自定义对象的属性和图标
        treeView.setCellFactory(new Callback<TreeView<CustomObject>, TreeCell<CustomObject>>() {
            @Override
            public TreeCell<CustomObject> call(TreeView<CustomObject> param) {
                return new TreeCell<CustomObject>() {
                    @Override
                    protected void updateItem(CustomObject item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            HBox hBox = new HBox();
                            Text nameText = new Text(item.getName());
                            Text descriptionText = new Text(" - " + item.getDescription());
                            ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/path/to/your/icon.png")));
                            imageView.setFitHeight(16);
                            imageView.setFitWidth(16);
                            hBox.getChildren().addAll(imageView, nameText, descriptionText);
                            setGraphic(hBox);
                        } else {
                            setGraphic(null);
                        }
                    }
                };
            }
        });

        // 添加鼠标点击事件处理器
        treeView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            TreeItem<CustomObject> item = treeView.getSelectionModel().getSelectedItem();
            if (item != null) {
                System.out.println("Selected item: " + item.getValue().getName() + " - " + item.getValue().getDescription());
            }
        });

        // 创建VBox布局并添加TreeView
        VBox vbox = new VBox(treeView);

        // 创建场景
        Scene scene = new Scene(vbox, 300, 300);

        // 设置舞台
        primaryStage.setTitle("Custom TreeView with Icons Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
