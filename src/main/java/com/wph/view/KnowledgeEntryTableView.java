package com.wph.view;

import com.demo.javafxexample.tableview.IkonliTableCellExample;
import com.wph.dto.KnowledgeEntryDTO;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.*;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

public class KnowledgeEntryTableView {
    public KnowledgeEntryTableView(){

    }
    public  TableView<KnowledgeEntryDTO> getTableView() {
        TableView<KnowledgeEntryDTO> tableView = new TableView<>();
        // 创建列
        TableColumn<KnowledgeEntryDTO, String> nameColumn = new TableColumn<>("Category");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<KnowledgeEntryDTO, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setCellFactory(TextFieldTableCell.forTableColumn());


        TableColumn<KnowledgeEntryDTO, String> addressColumn = new TableColumn<>("Content");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("content"));

        // 设置自定义单元格工厂
        addressColumn.setCellFactory(new Callback<TableColumn<KnowledgeEntryDTO, String>, TableCell<KnowledgeEntryDTO, String>>() {
            @Override
            public TableCell<KnowledgeEntryDTO, String> call(TableColumn<KnowledgeEntryDTO, String> param) {
                return new CopyableTableCell();
            }
        });


        TableColumn<KnowledgeEntryDTO, Void> actionColumn = new TableColumn<>("Action");
        actionColumn.setCellFactory(param -> new TableCell<>() {
            //            private final Button editButton = new Button("Edit");
            private final Hyperlink editButton = new Hyperlink("编辑");

            {
//                editButton.getStyleClass().setAll("btn","btn-info");
                //editButton.setMnemonicParsing(true);

                editButton.setOnAction(event -> {
                    KnowledgeEntryDTO entryDTO = getTableView().getItems().get(getIndex());
                    showEditForm(entryDTO, getTableView());
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
        tableView.getColumns().addAll(nameColumn, titleColumn, addressColumn, actionColumn);

        // 添加数据到 TableView
        tableView.getItems().addAll(KnowledgeEntryDTO.getKnowledgeEntryDTOs());
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        return tableView;
    }

    private static void showEditForm(KnowledgeEntryDTO entryDTO, TableView<KnowledgeEntryDTO> tableView) {
        // 创建表单界面
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(5);

        TextField categoryField = new TextField(entryDTO.categoryProperty().get());
        TextField titleField = new TextField(entryDTO.titleProperty().get());
        TextField contentField = new TextField(entryDTO.contentProperty().get());

        HTMLEditor htmlEditor = new HTMLEditor();
        htmlEditor.setHtmlText(entryDTO.contentProperty().get());
        form.addRow(0, new Label("Category:"), categoryField);
        form.addRow(1, new Label("Title:"), titleField);
        form.addRow(2, new Label("Content:"), contentField);
//        form.addRow(3, htmlEditor);
        form.add(htmlEditor, 0, 3, 2, 1);

        // 创建对话框
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setTitle("Edit");

        // 创建保存按钮
        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {

            entryDTO.setCategory(categoryField.getText());
            entryDTO.setTitle(titleField.getText());
            entryDTO.setContent(htmlEditor.getHtmlText());

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
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.showAndWait();
    }

    private class CopyableTableCell extends TableCell<KnowledgeEntryDTO, String> {
        private HBox hbox;
        private Label textLabel;
        private Button copyButton;
        private String content;


        public CopyableTableCell() {
            hbox = new HBox();
            textLabel = new Label();
            textLabel.setMinWidth(150); // 设置最小宽度
            textLabel.setPrefWidth(150); // 设置首选宽度
            textLabel.setMaxWidth(150); // 设置最大宽度

            // 使用 Ikonli 创建包含图标的按钮
            FontIcon copyIcon = new FontIcon(FontAwesomeSolid.COPY);
            copyButton = new Button("", copyIcon);
            // 自定义按钮样式
            copyButton.setStyle("-fx-background-color: transparent; " +
                    "-fx-border-color: transparent; " +
                    "-fx-text-fill: blue; " +
                    "-fx-underline: true;");
            // 添加悬停效果和手形光标
            copyButton.setOnMouseEntered(event -> {
                copyButton.setStyle("-fx-background-color: transparent; " +
                        "-fx-border-color: transparent; " +
                        "-fx-text-fill: darkblue; " + // 悬停时改变文本颜色
                        "-fx-underline: true;");
                copyButton.setCursor(Cursor.HAND); // 设置手形光标
            });

            copyButton.setOnMouseExited(event -> {
                copyButton.setStyle("-fx-background-color: transparent; " +
                        "-fx-border-color: transparent; " +
                        "-fx-text-fill: blue; " +
                        "-fx-underline: true;");
                copyButton.setCursor(Cursor.DEFAULT); // 恢复默认光标
            });

            copyButton.setOnAction(event -> copyToClipboard(content));

            // 填充器使按钮靠右
            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);
            hbox.getChildren().addAll(textLabel, spacer, copyButton);
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                content = item;
                textLabel.setText(item);
                setText(null);
                setGraphic(hbox);
            }
        }

        private void copyToClipboard(String text) {
            final Clipboard clipboard = Clipboard.getSystemClipboard();
            final ClipboardContent content = new ClipboardContent();
            content.putString(text);
            clipboard.setContent(content);
        }
    }
}
