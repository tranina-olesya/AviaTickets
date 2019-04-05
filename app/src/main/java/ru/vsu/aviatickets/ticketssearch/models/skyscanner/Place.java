package ru.vsu.aviatickets.ticketssearch.models.skyscanner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Place {
    @SerializedName("Id")
    @Expose
    private Integer id;

    @SerializedName("Code")
    @Expose
    private String code;

    @SerializedName("Type")
    @Expose
    private String type;

    @SerializedName("Name")
    @Expose
    private String name;

    public Place(Integer id, String code, String type, String name) {
        this.id = id;
        this.code = code;
        this.type = type;
        this.name = name;
    }

    public Place() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
