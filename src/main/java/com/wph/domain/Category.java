package com.wph.domain;

import com.wph.CodeNamePair;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private CodeNamePair parent;
    private List<CodeNamePair> children = new ArrayList<CodeNamePair>();

    public CodeNamePair getParent() {
        return parent;
    }

    public void setParent(CodeNamePair parent) {
        this.parent = parent;
    }

    public List<CodeNamePair> getChildren() {
        return children;
    }

    public void setChildren(List<CodeNamePair> children) {
        this.children = children;
    }

    public static List<Category> getCategories() {
        List<Category> categories = new ArrayList<Category>();
        Category br = new Category();
        br.setParent(new CodeNamePair("BR", "Booking Request"));
        br.getChildren().add(new CodeNamePair("br_business","BR Business Knowledge"));
        br.getChildren().add(new CodeNamePair("br_message","BR Message Samples"));
        br.getChildren().add(new CodeNamePair("br_integration","BR Integration Flow"));
        categories.add(br);

        Category si = new Category();
        si.setParent(new CodeNamePair("si", "Shipping Instruction"));
        si.getChildren().add(new CodeNamePair("si_business","SI Business Knowledge"));
        si.getChildren().add(new CodeNamePair("si_message","SI Message Samples"));
        si.getChildren().add(new CodeNamePair("si_integration","SI Integration Flow"));
        categories.add(si);

        Category jenkov = new Category();
        jenkov.setParent(new CodeNamePair("jenkov", "jenkov"));
        categories.add(jenkov);

        Category controlsfx = new Category();
        controlsfx.setParent(new CodeNamePair("controlsfx", "controlsfx"));
        categories.add(controlsfx);

        Category FormsFX = new Category();
        FormsFX.setParent(new CodeNamePair("FormsFX", "FormsFX"));
        categories.add(FormsFX);

        Category AtlantaFX = new Category();
        AtlantaFX.setParent(new CodeNamePair("AtlantaFX", "AtlantaFX"));
        categories.add(AtlantaFX);

        return categories;
    }
}
