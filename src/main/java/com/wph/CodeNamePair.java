package com.wph;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CodeNamePair {
    private String code;
    private String name;
    private StringProperty summerTimeZone = new SimpleStringProperty("CEST");

    public CodeNamePair copy() {
        return new CodeNamePair(code, name);
    }
    public CodeNamePair(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public StringProperty summerTimeZoneProperty() {
        return summerTimeZone;
    }
}
