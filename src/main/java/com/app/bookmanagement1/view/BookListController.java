package com.app.bookmanagement1.view;

import com.app.bookmanagement1.model.Book;
import com.app.bookmanagement1.viewmodel.BookViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class BookListController {
    @FXML
    private TableView<Book> bookTableView;

    private BookViewModel bookViewModel;

    public void initialize() {
        bookViewModel = new BookViewModel();
        bookTableView.setItems(bookViewModel.getBookList());
    }

    @FXML
    private void editBook(ActionEvent event) {
        Book selectedBook = bookTableView.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            // 打开编辑页面进行编辑
            // ...
        }
    }
}
