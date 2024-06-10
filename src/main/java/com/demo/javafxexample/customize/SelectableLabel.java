package com.demo.javafxexample.customize;

import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;

public class SelectableLabel extends Label {

    public SelectableLabel(String text) {
        super(text);
    }

    public void onMouseClicked(MouseEvent event) {

        if (event.getClickCount() == 2) {
            String selectedText = this.getText();
            ClipboardContent content = new ClipboardContent();
            content.putString(selectedText);
            Clipboard.getSystemClipboard().setContent(content);
        }
    }
}
