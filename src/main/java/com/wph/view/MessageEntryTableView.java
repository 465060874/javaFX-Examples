package com.wph.view;

import com.wph.dto.MessageEntryDTO;
import com.wph.dto.MessageEntryDTO;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

public class MessageEntryTableView {
    public TableView<MessageEntryDTO> getTableView() {
        TableView<MessageEntryDTO> tableView = new TableView<>();
        // 创建列
        TableColumn<MessageEntryDTO, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<MessageEntryDTO, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
//        descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());


        TableColumn<MessageEntryDTO, String> messagePropertyColumn = new TableColumn<>("MessageProperty");
        messagePropertyColumn.setCellValueFactory(new PropertyValueFactory<>("messageProperty"));

        // 设置自定义单元格工厂
//        messagePropertyColumn.setCellFactory(new Callback<TableColumn<MessageEntryDTO, String>, TableCell<MessageEntryDTO, String>>() {
//            @Override
//            public TableCell<MessageEntryDTO, String> call(TableColumn<MessageEntryDTO, String> param) {
//                return new MessageEntryTableView.CopyableTableCell();
//            }
//        });


        TableColumn<MessageEntryDTO, Void> actionColumn = new TableColumn<>("Action");
        actionColumn.setCellFactory(param -> new TableCell<>() {
            //            private final Button editButton = new Button("Edit");
            private final Hyperlink editButton = new Hyperlink("编辑");

            {
//                editButton.getStyleClass().setAll("btn","btn-info");
                //editButton.setMnemonicParsing(true);

                editButton.setOnAction(event -> {
                    MessageEntryDTO entryDTO = getTableView().getItems().get(getIndex());
                    //showEditForm(entryDTO, getTableView());
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
        tableView.getColumns().addAll(titleColumn, descriptionColumn, messagePropertyColumn, actionColumn);

        // 添加数据到 TableView
        tableView.getItems().addAll(MessageEntryDTO.getKnowledgeEntryDTOs());
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        return tableView;
    }
}
