package ru.vsu.aviatickets.api.entities.tripmodels;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchPlace that = (SearchPlace) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code);
    }
}
