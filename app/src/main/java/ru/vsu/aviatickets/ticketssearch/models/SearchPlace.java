package ru.vsu.aviatickets.ticketssearch.models;

import java.io.Serializable;

public class SearchPlace implements Serializable {
    private String name;
    private String code;

    public SearchPlace(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public SearchPlace(String name) {
        this.name = name;
    }

    public SearchPlace() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
