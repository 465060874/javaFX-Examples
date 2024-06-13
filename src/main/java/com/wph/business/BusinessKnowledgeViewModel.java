package com.wph.business;

import com.demo.javafxexample.apps.bkcourse.mvvmapp.EmploymentRequest;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

public class BusinessKnowledgeViewModel {
    private final StringProperty title = new SimpleStringProperty("");
    private final StringProperty shortDescription = new SimpleStringProperty("");
    private final StringProperty details = new SimpleStringProperty("");

    private final BusinessKnowledgeConverter converter =
            new BusinessKnowledgeConverter();

    private final BusinessKnowledgeModel model = new BusinessKnowledgeModel();

    public StringProperty titleProperty() {
        return title;
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty shortDescriptionProperty() {
        return shortDescription;
    }

    public String getShortDescription() {
        return shortDescription.get();
    }

    public StringProperty detailsProperty() {
        return details;
    }

    public String getDetails() {
        return details.get();
    }

    public void edit() {
//        EmploymentRequest data = converter.toEmploymentRequest( this );
//        model.save( data );
    }

    public void reset() {
//        this.name.set("");
//        this.position.set("");
//        this.annualSalary.set(0.0d);
    }

    public BusinessKnowledgeViewModel(String title, String shortDescription, String details) {
        this.title.set(title);
        this.shortDescription.set(shortDescription);
        this.details.set(details);
    }

    public static List<BusinessKnowledgeViewModel> businessKnowledgeViewModels() {
        List<BusinessKnowledgeViewModel> models = new java.util.ArrayList<>();
        models.add(new BusinessKnowledgeViewModel("Online BR", "Online BR主要内容", "detail"));
        models.add(new BusinessKnowledgeViewModel("EDI BR", "EDI BR主要内容", "testing"));
        return models;
    }
}
