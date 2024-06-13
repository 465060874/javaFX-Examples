package com.wph.business;

import java.util.List;

public class BusinessKnowledge {
    private String category;
    private String title;
    private String shortDescription;
    private String details;

    // Getters and setters


    public BusinessKnowledge(String category, String title, String shortDescription, String details) {
        this.category = category;
        this.title = title;
        this.shortDescription = shortDescription;
        this.details = details;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }


}
