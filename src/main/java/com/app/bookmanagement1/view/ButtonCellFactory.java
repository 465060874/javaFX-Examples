package com.app.bookmanagement1.view;

import com.app.bookmanagement1.model.Book;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class ButtonCellFactory implements Callback<TableColumn<Book, Void>, TableCell<Book, Void>> {
    @Override
    public TableCell<Book, Void> call(TableColumn<Book, Void> param) {
        return new TableCell<>() {
            private final Button editButton = new Button("Edit");

            {
                editButton.setOnAction(event -> {
                    Book book = getTableView().getItems().get(getIndex());
                    // 打开编辑页面进行编辑
                    // ...
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
        };
    }
}
