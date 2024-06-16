package com.app.bookmanagement1.view;

import com.app.bookmanagement1.model.Book;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class BookEditController {
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField authorTextField;
    @FXML
    private TextField priceTextField;

    private Book book;

    public void setBook(Book book) {
        this.book = book;
        nameTextField.setText(book.getName());
        authorTextField.setText(book.getAuthor());
        priceTextField.setText(String.valueOf(book.getPrice()));
    }

    @FXML
    private void saveBook() {
        book.setName(nameTextField.getText());
        book.setAuthor(authorTextField.getText());
        book.setPrice(Double.parseDouble(priceTextField.getText()));
        // 保存编辑后的图书信息
        // ...
    }
}