package com.wph.dto;

import com.wph.domain.KnowledgeEntry;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.List;

public class KnowledgeEntryDTO {
    private SimpleStringProperty category;
    private SimpleStringProperty title;
    private SimpleStringProperty content;

    public KnowledgeEntryDTO(KnowledgeEntry entry) {
        category = new SimpleStringProperty(entry.getCategory());
        title = new SimpleStringProperty(entry.getTitle());
        content = new SimpleStringProperty(entry.getContent());
    }

    public KnowledgeEntryDTO(String category, String title, String content) {
        this.category = new SimpleStringProperty(category);
        this.title = new SimpleStringProperty(title);
        this.content = new SimpleStringProperty(content);
    }
    public String getCategory() {
        return category.get();
    }

    public SimpleStringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getContent() {
        return content.get();
    }

    public SimpleStringProperty contentProperty() {
        return content;
    }

    public void setContent(String content) {
        this.content.set(content);
    }

    public static List<KnowledgeEntryDTO> getKnowledgeEntryDTOs() {
        List<KnowledgeEntryDTO> dtos = new java.util.ArrayList<>();
        dtos.add(new KnowledgeEntryDTO("BR", "Online BR", "Online BR主要内容"));
        dtos.add(new KnowledgeEntryDTO("BR", "EDI BR", "EDI BR主要内容"));
        dtos.add(new KnowledgeEntryDTO("BR", "Message Sample", "消息例子"));
        return dtos;
    }
}
