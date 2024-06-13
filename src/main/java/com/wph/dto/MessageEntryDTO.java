package com.wph.dto;

import javafx.beans.property.SimpleStringProperty;

import java.util.List;

public class MessageEntryDTO {
    private SimpleStringProperty title;
    private SimpleStringProperty description;
    private SimpleStringProperty messageProperty;
    private SimpleStringProperty messageContent;

    // 构造函数
    public MessageEntryDTO(String title, String description, String messageProperty, String messageContent) {
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.messageProperty = new SimpleStringProperty(messageProperty);
        this.messageContent = new SimpleStringProperty(messageContent);
    }

    // 省略 getter 和 setter 方法

    public SimpleStringProperty messageContentProperty() {
        return messageContent;
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public SimpleStringProperty messagePropertyProperty() {
        return messageProperty;
    }

    public static List<MessageEntryDTO> getKnowledgeEntryDTOs() {
        List<MessageEntryDTO> dtos = new java.util.ArrayList<>();
        dtos.add(new MessageEntryDTO("EDI BR", "New BR", "senderID:1111",""));
        dtos.add(new MessageEntryDTO("BKG2.0 BR", "New BR", "senderID:BKG2.0",""));
        dtos.add(new MessageEntryDTO("EDI BR", "Cancel BR", "senderID:1111",""));
        return dtos;
    }


}
