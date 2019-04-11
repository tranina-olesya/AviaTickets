package ru.vsu.aviatickets.ticketssearch.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Place {
    @SerializedName("Code")
    @Expose
    private String code;

    @SerializedName("Type")
    @Expose
    private String type;

    @SerializedName("Name")
    @Expose
    private String name;

    public Place(String code, String type, String name) {
        this.code = code;
        this.type = type;
        this.name = name;
    }

    public Place() {
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return Objects.equals(code, place.code) &&
                Objects.equals(type, place.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, type);
    }
}
